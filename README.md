## TotemDefender 1.12.2

The master branch will always contain the latest released version with possibly some bug fixes for that version that will eventually be released.
New versions will be created in branched named like {mc_version}-{mod_version}

#### IntelliJ IDEA

1. Execute the following gradle command to setup your workspace:

```
./gradlew setupDecompWorkspace
```
  
2. Open IntelliJ IDEA and import Gradle project

3. Close IntelliJ IDEA

4. Execute the following gradle command to create the necessary idea project files:

```
./gradlew genIntellijRuns
```

5. Select `Minecraft Client` from _Run configurations_ and press the green play button.
Minecraft should now launch with Binnie and all of its dependencies loaded :)
