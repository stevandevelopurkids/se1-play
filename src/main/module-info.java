/**
 * Modules have been introduced in Java 9 (in 2017) to compose software from
 * modularized projects. Prior, only packages within a project could be used.
 *
 * {@code module-info.java} indicates a <i>modularized</i> Java project. It
 * includes the module name: {@link se1.play}, external modules required by
 * this module and project packages opened and exported to other modules.
 * Opening a package makes it accessible to tools such as JUnit test runners.
 * Exporting a package makes it accessible to other modules.
 *
 * Locations of <i>required</i> modules must be provided via {@code MODULEPATH}.
 *
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
module se1.play {

    /*
     * Make package {@code application} accessible to other modules at compile
     * and runtime (use <i>open</i> for compile-time access only).
     */
    exports application;

    /* Open package to JUnit test runner. */
    opens application;

    /*
     * External module required by this module (JUnit-5 module for JUnit testing).
     */
    //requires org.junit.jupiter.api;
}