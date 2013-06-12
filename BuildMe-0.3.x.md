********************************************************************************************
THE README BELOW WAS CREATED FOR THE janux-0.3.x BRANCH AND IS OUT OF DATE AS OF 2011/08/09
The build was migrated to maven-2, and we kept the section below for reference temporarely.
CAUTION: DEPRECATED, to be removed soon.
********************************************************************************************

Thank you for your interest in Janux !


This readme file provides a short introduction on the project, describes
the project's directory layout, and provides instructions on setting up the
project, running the database integration tests, and running the reference Web
UI. More information on the project can be found at 

	http://www.janux.org


*** What is Janux ?

Janux is an Open Source eBiz Integration Infrastructure written in java that
aims to make it fast and cost-effective to interconnect disparate computer
systems to create 'super-applications' focused around:

  - people and human organizations
  - goods and services
  - inventory and pricing
  - eBusiness transactions
  - fulfillment logistics

While those are the business objectives that Janux aims to fulfill, Janux can
also be used as the starting point for building a Service Oriented
Application, if you are interested in the open-source technologies that Janux
leverages, such as Spring and Hibernate.


*** Project Layout

At the highest level, Janux is divided into two broad Areas of Concern, which
are themselves further sud-divided into smaller modules, found under the
'modules' directory.

The first broad area of concern is the Business Domain centered around generic
eCommerce, Fulfillment, Customer Relationship and Content Management needs.
This broad area of concern can be found under 'modules/biz'.  At the logical
level, these classes are found under the biz.janux root java package.

The second broad area of concern is orthogonal to the above, and deals with
all the Infrastructure that is necessary to create or integrate eBiz
services that may be deployed in-memory, or distributed across the network.
This infrastructure is found under the following two packages:

- modules/bus: deals with common infrastructure needs, such as security, session
	management, messaging, etc... At the logical level, rooted at the
	org.janux.bus java package

- modules/adapt: provides a way to define and store metadata and data for
	custom attributes that may be needed to extend internal or external
	applications.  At the logical level, rooted at the org.janux.adapt java
	package.


Janux is User Interface agnostic, in that it provides core 'headless' business
services, that are independent from any UI technology or Web Framework
development used to access them.  In particular, User Interfaces may communicate
with Janux via platform-independent remote protocols such as XML over HTTP,
and could be built using non-java technology, such as
php/perl/python/ruby/.NET/asp etc...

Nevertheless, Janux provides a reference Web User Interface for the different
business services.  This reference implementation uses SpringMvc, Tiles and
Velocity.  This reference UI, as well as UI infrastructure components can be
found under the 'ui' directory.


Janux leverages maven-1.x as a build tool (see http://maven.apache.org/maven-1.x).

As such, within each of the sub-folders of the 'modules' and 'ui' top-level
folders exists individual maven projects with the following internal
dependencies:

  - modules/bus:   no dependencies, stand-alone
  - modules/adapt: depends on modules/bus
  - modules/biz:   depends on both of the above

  - ui/biz:        depends on all of the above


As suggested by Maven best practices, we strive to minimize the amount of
custom scripting within the build files, and maintain the projects as 'pure'
maven projects.

We use Maven's ability to maintain projects as parent/child projects.  Thus,
at the root folder level of the overall project, you will find the standard
maven files:

	- project.xml
	- project.properties
	- maven.xml

The configuration settings in these files are shared by all sub-projects in
both the 'modules' and 'ui' sub-folders.

In turn, inside each individual project (modules/bus, modules/adapt, ui/biz,
etc..) you may find maven configuration files which extend or override
settings defined at the top-level.

If you need to customize these settings for your own needs (for example to
specify database connection settings or file locations), we suggest that
you follow the Maven best practice of:

	- copying the corresponding 'project.properties' file and renaming it 'build.properties', 
	- overriding whatever settings you need to override in build.properties,
	- leaving project.properties untouched, and your own build.properties out of version control.


*** Project Dependencies

In order to build the project, you will need to install Maven-1.x found at:
	
	http://maven.apache.org/maven-1.x
 

We recommend that you use maven-1.1 if you want to run the optional hibernate
code generation goals (targets) that have been defined for the project (read
more below).

If you do not intend to run the hibernate code generation tools, maven-1.0.2
should properly build the project.

We do not yet support maven-2.0, since we have not yet subscribed to the idea
of giving up scripting of the build files altogether, as required by maven-2.0.

Third party Library dependencies (jakarta commons, Spring, Hibernate etc..)
are automatically handled by Maven, although you may want to download the full
distribution and documentation for selected libraries for your own reference
and benefit.  

You can consult the project.xml at the root-level, and the individual
project.xml of the sub-projects for the different packages required, and their
version.

*** Installing the 'javaapp' plugin

In order to build the project, you will need to install the
Maven 'javaapp' plugin:

  1)  run  "maven plugin:download" from a command prompt
  2)  when prompted for the artifactId, type in "maven-javaapp-plugin"
  3)  when prompted for the groupID, type in "maven-plugins"
  4)  when prompted for the version, type in "1.3.1"
  5)  Maven will download and install the javaapp plugin from the
      ibiblio site
      
*** Building and Installing pre-packaged libraries

There are several group of open-source libraries that have to be included in
different projects, such as the hibernate run-time dependencies.

In order to keep track of these dependencies as a group, we have created several
projects under the 'lib' folder which use the maven javaapp plugin to aggregate
various jars distributions into a single one.

These pre-packaged libs should be installed in your local maven repository prior
to building the modules.  To do so, go to the folder 'lib' and run:

maven multiproject:install

      
*** Database dependencies

Janux aims to be agnostic about the persistence mechanism (Relational
Database, LDAP, etc..), and the specific database used.  Nevertheless,
currently Janux is heavily database-centric, and  we have initially targeted:

  - MySql-5.x 
  - Postgresql-7.4. 
	
as our persistent storage. Later versions of these databases should work as
well.  

We do not yet officially support other modern databases such as Oracle, DB2,
MS-SQL, Sybase, etc... but would be very interested in anyone willing to test
the distribution on other databases.  

The hibernate code generation tools (read below) make it fairly easy to
generate sql code for specific databases, but of course, these deployments
should be thoroughly tested before they are contributed.


*** Configuring the database

You will need to have mysql-5.x or postgresql-7.4 installed (the postgresql
version of the project may be slightly behind the mysql version) to run the
integration tests and the web ui.

For illustration sake, we assume here that you are using mysql, substitute
with the appropriate steps for your database otherwise.

Perform the following steps (one time only):

- create a database named 'Janux'  with user 'janux' (or replace with your own
	settings):

	mysql> create database Janux;
	mysql> grant all on Janux.* to janux@localhost identified by 'janux';

- copy 'project.properties' to 'build.properties' at the root level and change
	any appropriate variables, in particular the database connection paramaters,
	to reflect the values above

- If you are planning to run the web UI, make sure that the mysql jdbc driver
	and the jta jars (containing the javax.transaction) are available to your
	application server, for example by copying them to $CATALINA_HOME/common/lib
	if you are running tomcat.


*** Building the project 

Note: If you are on Windows do the following steps 
- In your file C:\pathToJanux\janux\build.properties adjust the log dir 
property to:
janux.log.dir=C:/pathToJanux/janux/logs

- create a file C:\pathToJanux\janux\modules\biz\build.properties and add the 
 log dir property: 
janux.log.dir=C:/pathToJanux/janux/modules/biz/logs

This way the file separator is the / character which is correctly interpreted by log4j.


Once a database has been configured as indicated above you can create jar
files for all modules by executing the following maven command on the command
line from the root of the project:

	maven multiproject:install

Or if you want to run a clean install after a failed install:

	maven multiproject:clean multiproject:install

See the maven documentation on the multiproject plug-in for more.

This command will build individual:

  - janux-bus.jar 
	- janux-adapt.jar
	- janux-biz.jar 

and install them in your local repository.

Alternatively, you can go to modules/bus, modules/adapt and modules/biz (in
that order) and run:

	maven jar:install
	or
	maven clean jar:install

to accomplish the same objective.


*** Create Database
Once this is done, you can clear/recreate the database by doing:

	$ cd modules/biz
	$ maven db-seedTest

In other words, make sure that you are in the 'modules/biz' folder in order to
create the database.

This goal will run two other goals 'db-recreate' and 'db-seed', which can be run
individually to troubleshoot specific problems.

These goals run sql scripts found under modules/biz/sql using the standard
mysql and psql client utilities.  Hence, the scripts (or parts of) can also be
run on the command line individually to troubleshoot specific issues.

*** Integration tests

When building the project, the build process will, by default, run integration
tests using either a pre-configured mysql or postgresql database.  

If you simply want to compile the code or generate javadoc documentation,
create a build.properties text file at the root of the project with the
following setting:

	maven.test.skip=true

Of course, we recommend that you install and pre-configure a database as
indicated below, since otherwise you will not be able to experience much of
the Janux functionality.


*** Building the sample UI

Once you have built the modules for the project, you may go to the ui/biz project and run:

	maven war
	or
	maven clean war

to build a war file that you can drop into your favorite web application
server (we use Tomcat-5.5.x) and preview the reference UI.


*** Running the hibernate code generation tools

We have configured the tools provided by the hibernate tools project for
ease-of-use from within the maven build.  Note that these tools are used for
convenience only, and no part of the Janux project relies on code-generation.  

We subscribe to the view that code-generation can save time, but that the
design of the project should not be affected by the quirks of code-generation
processes.  We have not yet found a code-generation methodology that is
open-source, easy to set up, provides true round-trip engineering without
introducing unwanted artifacts in the source code or interfaces, and satifies
all developers in a project.  If you find such a methodology, please share it
with us.

Nevertheless, the hibernate code-generation tools can be very useful, in
particular to determine whether a given hibernate mapping corresponds to the
database schema that we are visualizing, or to generate an initial version of
a java class from the mappings.

In order to run these tools you will need to have maven-1.1 installed, as
these tools require ant-1.6.x which, as of this writing, is not available in
maven-1.0.2.  

Alternatively, you can use ant-1.6.x and cut-and-paste the correspondings
targets in the maven.xml file to your ant build.xml file to run these tools.

To run these tools, go to any of the modules folders (you may want to start
with modules/biz) and type one of:

	- maven hbm2sql 
	
	creates DDL scripts under target/schema/mysql (or posgresql) from the
	hibernate mapping files found under src/resources/mappings

	- maven hbm2java

	creates java source code under target/src from the hibernate mapping files
	found under src/resources/mappings

	- maven db-doc

	creates handy javadoc-looking docs that document your database schema
