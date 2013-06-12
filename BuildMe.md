BuildMe for Janux-0.4.02 as of 2013-06-11
==========

This buildme file provides a short introduction on the project, and provides instructions on
preparing your system to run the build, running the maven2 build, and general information on
getting started with the many sub-components of the project.

*** System Requirements

In order to run the build, you will need to install:

  - java-1.5.x sdk or higher, 
  - maven-2.2.x,
  - mysql-5.x or higher (if you plan to use the components that implement db persistence)

While by default we use mysql, We intend to support Postgresql, and an in-memory db likey Derby on
HyperSql in the future.  Most of the database layer is implemented using hibernate, so it should be
relatively straight-forward to convert to a different db supported by hibernate in the future.


*** Database configuration

If you intend to run the database persistence projects (you could choose to build only the api
projects or the javadocs) you need to parametrize the build with the user name and password of a
mysql administrative super-user that has the ability to drop and create databases, and has the GRANT
option to create/edit/delete users and their permissions.

By default, this administrative user is set to 'root' with no password, which happens to work with
most unsecured plain-vanilla mysql installations.

Should you want to use a different admin user, or if you have secured your root mysql user with a
password, or if you would like to change the default database settings, add and edit the following
profile to your $HOME/.m2/settings.xml to suit your needs:

 [...]
  <profiles>
    <!-- default profile, enabled for all projects -->
    <profile>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <properties>
				<janux.db.host>localhost</janux.db.host>
				<janux.db.port>3306</janux.db.port>
				<janux.db.admin.user>root</janux.db.admin.user>
				<janux.db.admin.password>aG00dP@ssw0rd</janux.db.admin.password>
				<janux.db.user>janux</janux.db.user>
				<janux.db.password>janux</janux.db.password>
      </properties>
    </profile>
  </profiles>
 [...]

To learn more about how the databases are created using maven2 plugins, and to see the hibernate
configuration, and the hibernate tools plugin configuration, look into the file:

	build/template/persist-hibernate/pom.xml


*** Running the build

Assuming that you have taken all the steps above, and that you are feeling lucky, you can run a
full build by going to the root folder and running the standard maven-2 install command:

	$ cd $JANUX_HOME
	$ mvn install

	or
	$ mvn clean install

	or, to verify that the build compiles before runnning the tests:
	$ mvn -Dmaven.test.skip=true install


If you:

  - have any issues with the build such as downloading the project's dependencies, 
  - prefer to first download all dependencies before attempting a build, or
  - prefer a more gradual approach, 

you can build the project in stages by opening the root level pom.xml,
consulting the <modules> section:

[...]
<modules>
    <module>build</module><!-- build meta information -->
		<module>api</module>
		[...]
</modules>
[...]

and building each module in succession:


 $ cd $JANUX_HOME
 $ cd build
 $ mvn install
 [...]
 $ cd ../api
 $ mvn install
 [...]
 and so on..

Below are more details regarding the current directory layout of the project, and the organization
of the build.


*** Project Build Organization
		 
The organization of the project is still in flux as of this writing (2012-01-04), while the
information below should be useful, we do not guarantee its complete accuracy. 

Reading through the pom.xml files of the modules section of the root level $JANUX_HOME/pom.xml is a
good way to learn about the build, and this is the approach that we take below, as of this date.

Note that $JANUX_HOME/pom.xml is used to orchestrate a full build of the project, but is
not the 'parent' pom for the project from an inheritance perspective.  The parent pom is
build/pom.xml, read below.

	build/

	The build folder contains build metadata and factors out the following build aspects for the rest
	of the project:

		build/pom.xml
		Contains the general project information, and is the 'parent' pom file from which most, if not
		all, other pom files eventually derive.

		build/lib
		These pom files define groups of commonly used dependencies (such as database, Spring or
		Hibernate dependencies), that can be included in other projects.  They make it possible to manage
		group of dependencies in a central known place

		build/template
		Contains 'template' poms that can be extended to create certain types of projects. As of this

		build/template/persist-hibernate
		Contains a single 'persist-hibernate' template which can be used to configure projects that need
		to configure a Spring-Hibernate persistence layer, dump/create a database during testing to exercise
		this persistence layer, and run the hibernate tools

	api/

	This folder contains all the java interfaces, and abstract base classes of the project. By running
	mvn javadoc:javadoc in this project you can get a quick overview of the package structure and
	object module of the various modules.

	It creates a lightweight janux-api.jar that depends on a small set of dependencies, and is useful to
	prevent circular dependencies between implementations modules. So for example, by including the
	jar it is possible to compile against Service interfaces without importing all the Spring/Hibernate
	dependencies or other implementation artifacts that the module implementing that Service may contain.

	modules/

	This folder currently contains the janux-biz and janux-bus modules that first originated in the
	janux-0.3.x version of the code, and will be refactored in the future to make them more modular.
	For example, a 'security' module will be separated from the general 'bus' module, and a
	'geography' module will be separated from the general 'biz' module.
	
	sandbox/

	This is an incubator folder that makes it possible to create and organize new modules.  The
	expectation is that an initial organization for a project becomes more clear, the modules in the
	sandbox folder are eventually moved in a proper organization under the 'modules' folder.

	ui/

	Intended to contain reference UIs for the project.

	zDeprecated/

	Temporary dumping ground to which move deprecated modules before completely removing them.
