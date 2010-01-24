Jack Servlet
============

Requirements
------------

* Narwhal: http://narwhaljs.org/

AppEngine Instructions
----------------------

1. `tusk install jack jack-servlet appengine-java-sdk` to install the necessary dependencies.
2. `jack-servlet-init` in the directory containing your "jackconfig.js" (note: there must not be an existing Jakefile in this directory).
3. `jake runserver` to run locally or `jack update` to deploy to AppEngine.

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
