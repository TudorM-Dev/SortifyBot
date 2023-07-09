//using UnityEditor;
//using UnityEngine;

//[CustomEditor(typeof(RobotController))]
//public class RobotManagerWindow : Editor
//{
//    bool firstRun = true;
//    public static float baseAngle = 180f;
//    public static float liftHeight = 0.8f;
//    public static float jointAngle = 180f;
//    public static float clawAngle = 0f;

//    RobotController robotController;

//    public override void OnInspectorGUI()
//    {
//        robotController = (RobotController)target;


//        GUILayout.Label("Stepper position", EditorStyles.boldLabel);
//        EditorGUILayout.Space();

//        baseAngle = EditorGUILayout.Slider(baseAngle, 0.0f, 360.0f);
//        liftHeight = EditorGUILayout.Slider(liftHeight, 0.0f, 1.0f);
//        jointAngle = EditorGUILayout.Slider(jointAngle, 0.0f, 360.0f);
//        clawAngle = EditorGUILayout.Slider(clawAngle, 0.0f, 360.0f);

        

//        //if (updateValue())
//        //{
//        //    robotController.baseAngle = baseAngle;
//        //    robotController.liftHeight = liftHeight;
//        //    robotController.jointAngle = jointAngle;
//        //    robotController.clawAngle = clawAngle;
//        //    robotController.AngleUpdate();
//        //}
        
//    }


//    bool updateValue()
//    {
//        return (robotController.baseAngle != baseAngle || robotController.liftHeight != liftHeight || robotController.jointAngle != jointAngle || robotController.clawAngle != clawAngle);
//    }
//}
