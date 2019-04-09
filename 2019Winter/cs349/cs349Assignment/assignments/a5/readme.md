# A5: Direct Manipulation & Scene Graphs (Java/Android) -- Ragdoll
### Compiling
  - Navigate to a5/ at the same level of app folder
  - Run gradle build, it will automatically build the project
  - Then run this Fotag with virtual device with Pixel using Android Studio
  
### Operation
  - Touching and dragging the torso should move the entire paper doll. The torso cannot be rotated.
  - The head can tilt left and right relative to the torso, but should not deviate more than 50 degrees in either direction from the primary axis defined by the torso. You may define where the rotation point is for the head (i.e. at the torso, or some other location, such as in the head).
  - The upper arm is attached to the torso and may rotate an entire 360 degrees about its point of attachment to the torso. When rotating the upper arm, the lower arm should retain its same relative orientation to the upper arm. For example, if the lower arm is at a 30 degree angle relative to the upper arm, and the upper arm is rotated, the lower arm should retain its 30 degree angle relative to the upper arm.
  - the lower arm should have a movement range of 135 degrees in either direction relative to the primary axis defined by the upper arm.
  - The hand can pivot 35 degrees in either direction relative to the lower arm. It should maintain its same relative orientation to the lower arm independent of any rotations of the lower or upper arm.
  - The upper leg can pivot 90 degrees in either direction relative to the primary axis defined by the torso.
  - The lower leg can also pivot 90 degrees in either direction relative to primary axis defined by the upper leg.
  - Assume the feet are attached at a 90 degree angle to the lower leg. Given this, they can pivot 35 degrees in either direction from this initial orientation.
  - The upper and lower legs can each be "stretched" by scaling them along their primary axes. Other parts, such as the feet, however, should stay at the same 
  scale (i.e., feet do not scale). Furthermore, scaling the upper leg should scale the corresponding lower leg simultaneously.
