package groovy.memory.leak;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyShell;
import groovy.lang.GroovySystem;
import groovy.lang.Script;
import org.codehaus.groovy.runtime.InvokerHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class GroovyUtil {

	public static void executeScriptWithClose(Binding binding, String script) throws IOException {
		String groovyFilename = "script-" + UUID.randomUUID() + ".groovy";
		FileOutputStream fileOutputStream = new FileOutputStream(groovyFilename);
		fileOutputStream.write(script.getBytes(StandardCharsets.UTF_8));
		fileOutputStream.close();

		GroovyCodeSource groovyCodeSource = new GroovyCodeSource(new File(groovyFilename));
		groovyCodeSource.setCachable(false);

		URLClassLoader urlClassLoader = URLClassLoader.newInstance(new URL[]{});
		GroovyClassLoader groovyClassLoader = new GroovyClassLoader(urlClassLoader);
		Class groovyClass = groovyClassLoader.parseClass(groovyCodeSource, false);
		Script groovyScript = InvokerHelper.createScript(groovyClass, binding);
		groovyScript.run();

		GroovySystem.getMetaClassRegistry().removeMetaClass(groovyScript.getMetaClass().getTheClass());
		GroovySystem.getMetaClassRegistry().removeMetaClass(groovyClass);
		groovyClassLoader.clearCache();
		groovyClassLoader.clearAssertionStatus();
		groovyClassLoader.close();
		urlClassLoader.clearAssertionStatus();
		urlClassLoader.close();

		Files.deleteIfExists(Paths.get(groovyFilename));
	}

	public static void executeScriptWithoutClose(Binding binding, String script) throws IOException {
		GroovyCodeSource groovyCodeSource = new GroovyCodeSource(script, "SCRIPT", GroovyShell.DEFAULT_CODE_BASE);
		GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
		Class groovyClass = groovyClassLoader.parseClass(groovyCodeSource, false);
		Script groovyScript = InvokerHelper.createScript(groovyClass, binding);
		groovyScript.run();
	}
}
