using System.Collections;
using System.Collections.Generic;
using System.Threading;
using UnityEngine;

public enum RobotState{
    IK,
    Manual
}

public class RobotController : MonoBehaviour
{
    public static bool gameStarted = false;

    private float downPos = 0.08f;
    private float upPos = 0.335f;

    private float maxDisIK = 36.8f;
    // Start is called before the first frame update
    [Header("Transform Components")]
    public Transform baseTransform;
    public Transform liftTransform;
    public Transform jointTransform;
    public Transform clawTransform;
    public Transform ik_Target;
    public Transform ik_Follow;

    [Header("Sliders")]
    [Range(0.0f, 360.0f)] public float baseAngle;
    [Range(0.0f, 1.0f)] public float liftHeight;
    [Range(0.0f, 360.0f)] public float jointAngle;
    [Range(0.0f, 360.0f)] public float clawAngle;

    public float A;
    public float B;
    public float C;

    [Header("Utils")]
    public RobotState robotState = RobotState.Manual;

    ServerClient serverClient;

    // public bool use_IK = false;
    void Start()
    {
        serverClient = new ServerClient();

        //Thread angleThread = new Thread(AngleUpdate);
        //angleThread.Start();
    }

    // Update is called once per frame
    void Update()
    {
        AngleUpdate();

        ////////// Debug.Log(maxDisIK +":" + getXDistance().ToString() +"="+getRatio().ToString());
    }

    public void AngleUpdate(){

        if (serverClient.inputAvailable())
        {
            baseAngle = serverClient.baseAngle;
            liftHeight = serverClient.liftHeight;
            jointAngle = serverClient.jointAngle;
            clawAngle = serverClient.clawAngle;
        }

        if (robotState == RobotState.IK) {
            // gameObject.GetComponent<Animator>().enabled = false;
            InverseKinematics();
        }
        else{
            // gameObject.GetComponent<Animator>().enabled = true;
        }
        // else gameObject.GetComponent<Animator>().enabled = true;
        baseTransform.eulerAngles = new Vector3(baseTransform.eulerAngles.x, baseAngle+17.5f+180f, transform.eulerAngles.z);
        liftTransform.localPosition = new Vector3(liftTransform.localPosition.x, (downPos + (upPos - downPos)*liftHeight), liftTransform.localPosition.z);
        jointTransform.eulerAngles = new Vector3(baseTransform.eulerAngles.x, jointAngle+baseAngle + 223+17.5f, transform.eulerAngles.z);
        clawTransform.eulerAngles = new Vector3(baseTransform.eulerAngles.x, clawAngle+baseAngle+jointAngle + 223+17.5f+40, transform.eulerAngles.z);
    }

    void InverseKinematics(){

        getAngle(getXDistance(), maxDisIK/5*2, maxDisIK/5*3);
        jointAngle = A-180;


    }

    void getAngle(float sideA, float sideB, float sideC){
        Debug.Log(sideA.ToString() + ' ' + sideB.ToString() + ' ' +sideC.ToString());
        float cosA = (sideB * sideB + sideC * sideC - sideA * sideA) / (2 * sideB * sideC);
        float cosB = (sideA * sideA + sideC * sideC - sideB * sideB) / (2 * sideA * sideC);
        float cosC = (sideA * sideA + sideB * sideB - sideC * sideC) / (2 * sideA * sideB);

        float radToDeg = 180 / Mathf.PI;

        A = Mathf.Acos(cosA) * radToDeg;
        B = Mathf.Acos(cosB) * radToDeg;
        C = Mathf.Acos(cosC) * radToDeg;

    }

    float getRatio(){
        return getXDistance()/maxDisIK;
    }

    float getXDistance(){
        return Mathf.Abs(ik_Follow.position.x - ik_Target.position.x)*100;
    }

    public static double CalculateAngle(double sideA, double sideB, double sideC)
    {
    double cosA = (sideB * sideB + sideC * sideC - sideA * sideA) / (2 * sideB * sideC);
    double angleA = Mathf.Acos((float)cosA);
    return angleA * 180 / Mathf.PI;
    }
}
