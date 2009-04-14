Jack Servlet
============

AppEngine Instructions
----------------------

1. Set the APPENGINE_JAVA_SDK environment variable to the location of the AppEngine SDK, or change the "sdk.dir" property in the build.xml file.
2. Ensure a Narhwal distribution is located in the parent directory and is named "narwhal" (alternatively, replace or modify the "war/WEB-INF/narwhal" with a Narwhal distribution)
3. Add "jack.js" and the "jack" (or symlinks) from the lib directory in a Jack distribution to the lib directory of Narwhal.
4. Place your Jack application in "war/WEB-INF/app/" with the main file called "app.js". The last statement of this file should be a Jack compatible function (alternatively, in "war/WEB-INF/web.xml" set a parameter named "module" to the name of the main file, and/or a paramter named "function" to the name of the function to use)
5. "ant runserver" to run locally.
6. Edit the AppEngine application ID in "war/WEB-INF/appengine-web.xml".
7. "ant update" to deploy.

Other Servlet Container Instructions
------------------------------------

Coming soon.

Notes
-----

* The package loader doesn't currently work with AppEngine due to security restrictions on classloaders. Copy the JavaScript files and directories from additional packages to Narwhal's "lib" directory, and jars to "war/WEB-INF/lib".

* In addition to the "war/WEB-INF/narwhal" symlink, there are relative symlinks to Rhino in "war/WEB-INF/lib/js.jar" and an example application in "war/WEB-INF/app/app.js"