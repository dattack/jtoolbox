[![Build](https://github.com/dattack/jtoolbox/actions/workflows/maven-deploy.yml/badge.svg?branch=dev)](https://github.com/dattack/jtoolbox/actions)
[![license](https://img.shields.io/:license-Apache-blue.svg?style=plastic-square)](LICENSE.md)
[![Maven Central](https://img.shields.io/maven-central/v/com.dattack/jtoolbox.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.dattack/jtoolbox)
[![javadoc](https://javadoc.io/badge2/com.dattack/jtoolbox/javadoc.svg)](https://javadoc.io/doc/com.dattack/jtoolbox)

# jToolbox

jToolbox is a set of utility classes that simplify the programming of Java code. These
classes can be used by many projects instead of solving tedious problems over and over
again. jToolbox consists of the following modules:

- jtoolbox-core: utility classes that only depends on JDK.
- jtoolbox-commons-configuration: utility classes that extends Apache commons-configuration.
- jtoolbox-commons-email: utility classes that extends Apache commons-email.

## Where can I get the latest release?

You can pull it from the Central Maven repositories:

```xml
  <dependency>
    <groupId>com.dattack</groupId>
    <artifactId>jtoolbox-core</artifactId>
    <version>0.7-SNAPSHOT</version>
  </dependency>
  
  <dependency>
    <groupId>com.dattack</groupId>
    <artifactId>jtoolbox-commons-configuration</artifactId>
    <version>0.7-SNAPSHOT</version>
  </dependency>
  
  <dependency>
    <groupId>com.dattack</groupId>
    <artifactId>jtoolbox-commons-email</artifactId>
    <version>0.7-SNAPSHOT</version>
  </dependency>
```

The source code on the master branch is the current state of development; it is not
recommended for general use. If you prefer to build from source, please use an appropriate
release tag.

## Contributing

Pull requests and stars are always welcome. For bugs and feature
requests, [please create an issue](https://github.com/dattack/jtoolbox/issues).

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

If you have other questions, please contact by [email](mailto:dev@dattack.com) or
[@dattackteam](https://twitter.com/dattackteam)

## License

Code is under the [Apache Licence v2](https://www.apache.org/licenses/LICENSE-2.0.txt).
