Jack Servlet
============

AppEngine Instructions
----------------------

1. Set the APPENGINE_JAVA_SDK environment variable to the location of the AppEngine SDK (http://code.google.com/appengine/downloads.html), or change the "sdk.dir" property in the build.xml file.
2. Ensure "war/WEB-INF/narwhal" is a Narwhal distribution (currently symlinked to "narwhal" in the parent directory) and "war/WEB-INF/narwhal/packages/jack" is a Jack distribution.
4. Place your Jack application in "war/WEB-INF" with the main module called "jackconfig.js", which exports the main Jack application as "app". 
5. "ant runserver" to run locally.
6. Edit the AppEngine application ID in "war/WEB-INF/appengine-web.xml".
7. "ant update" to deploy.

Other Servlet Container Instructions
------------------------------------

Coming soon.

Notes
-----

* You can change the default modules path, module name, application name, environment name using the "modulesPath", "module", "app", and "environment" init-params in web.xml, i.e.:

    <servlet>
      <servlet-name>jack</servlet-name>
      <servlet-class>org.jackjs.JackServlet</servlet-class>
      <init-param>
        <param-name>modulesPath</param-name>
        <param-value>WEB-INF</param-value>
      </init-param>
      <init-param>
        <param-name>module</param-name>
        <param-value>jackconfig.js</param-value>
      </init-param>
      <init-param>
        <param-name>app</param-name>
        <param-value>app</param-value>
      </init-param>
      <init-param>
        <param-name>environment</param-name>
        <param-value>production</param-value>
      </init-param>
    </servlet>

* In addition to the "war/WEB-INF/narwhal" symlink, there are relative symlinks to Rhino in "war/WEB-INF/lib/js.jar" and an example application at "war/WEB-INF/jackconfig.js"
