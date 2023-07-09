using System.Collections;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using UnityEngine;

public class ServerClient {
    // Start is called before the first frame update

    string clientInput = "";
    bool gotMsg = false;

    public float baseAngle = 0f;
    public float liftHeight = 0f;
    public float jointAngle = 0f;
    public float clawAngle = 0f;

    public ServerClient()
    {
        Thread threadData = new Thread(OutputData);
        threadData.Start();
    }

    void OutputData()
    {
        // a server which will receive the packet
        byte[] data = new byte[1024];
        IPEndPoint endpoint = new IPEndPoint(IPAddress.Any, 8888);
        Socket sck = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);
        sck.Bind(endpoint);
        Debug.Log("Waiting for a client...");

        while (true)
        {
            EndPoint remote = (EndPoint)endpoint;
            int recv = sck.ReceiveFrom(data, ref remote);
            clientInput = Encoding.ASCII.GetString(data, 0, recv);
            setAngleFromInput(clientInput);
            gotMsg = true;
        }
    }

    public bool inputAvailable()
    {
        if (gotMsg){ gotMsg = false; return true; }
        else return false;
    }

    public void setAngleFromInput(string line)
    {
        string[] numberStrings = line.Split(' ');

        // Extract the first four numbers
        int count = Mathf.Min(numberStrings.Length, 4);
        float[] numbers = new float[count];
        for (int i = 0; i < count; i++) { numbers[i] = float.Parse(numberStrings[i]); }

        foreach (int number in numbers)
        {
            Debug.Log(number);
        }

        baseAngle = numbers[0];
        liftHeight = numbers[1];
        jointAngle = numbers[2];
        clawAngle = numbers[3];

    }
}
