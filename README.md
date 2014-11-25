# ShiftFocus UUID Library

This is just a simple library to provide a convenient Scala wrapper around
the built-in Java UUID class. It provides convenience methods for creating
random UUIDs, and converting to/from string and JSON representations.

To use, first ensure you have added the ShiftFocus maven repository to your
resolvers:

    resolvers += "ShiftFocus" at "https://maven.shiftfocus.ca/repositories/releases"

And then include the library in your dependencies:

    libraryDependencies += "ca.shiftfocus" %% "uuid" % "1.0.0"

Then you can import the library for use in your Scala files like so:

    import ca.shiftfocus.uuid.UUID
    
