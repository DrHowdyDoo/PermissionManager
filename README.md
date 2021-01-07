# PermissionManager
Simple class to manage your app's permission at runtime.

How to use this class :

-> Just add this class to your project.
-> In your activity or fragment create a new object of the PermissionManager class by passing the activity context :

   PermissionManager pm = new PermissionManager(Context context); // Pass your activity context 
   
-> After creating an object simply access its method for checking or asking for runtime permissions :

   pm.permissionCheck(String permission); // Pass the permission, you want to check.
   pm.askPermission(String permission,int reqCode);  // Pass the permission, you want to ask at runtime and a request code. 
   
   Note : reqCode should be unique for every permission asked in the same Activity
   
   pm.specialPermissionCheck(String per,String opCode); // Method to check for Special permissions at runtime. These permissions are also known as dangerous permissions.
                                                        // You also have to pass an opCode for the permission. You can get those from the documentation 
                                                        
   pm.askSpecialPermission(String per,int reqCode);  // Method to ask for special/dangerous permission at run time.   

   NOTE : askSpecialPermission will take the user directly to the settings page of the permission and hence it is advised to first show a dialog to the user and ask them if they             want to go to the settings page for the permission or not. If yes, then you can call this method.
  
  
  -> If you want to do something in response to the user response to the permissions, you can override onRequestPermissionsResult method for normal permissions and   
     onActivityResult method for special permissions as special permission was asked by sending an intent with an action.
     
     These methods will have requestCode as one of their parameters which you have passed in askPermission and askSpecialPermission methods.
     
      
     
  
  

