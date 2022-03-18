package groovy.memory.leak;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovySystem;
import groovy.lang.Script;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/*
 * VM OPTIONS 
 * -Dgroovy.use.classvalue=true 
 * -XX:+ClassUnloadingWithConcurrentMark 
 * -XX:+CMSScavengeBeforeRemark 
 * -XX:+ScavengeBeforeFullGC 
 * -XX:ParallelGCThreads=2 
 * -XX:MetaspaceSize=50M 
 * -XX:MaxMetaspaceSize=50M 
 * -XX:CompressedClassSpaceSize=30M 
 * -XX:MinMetaspaceFreeRatio=0 
 * -XX:MaxMetaspaceFreeRatio=100
 */

public class GroovyMemoryLeakWithClose {

	private static final Logger logger = LoggerFactory.getLogger(GroovyMemoryLeakWithClose.class.getName());
	
	public static void main(String[] args) throws InterruptedException {
		GroovySystem.setKeepJavaMetaClasses(false);
		GroovySystem.stopThreadedReferenceManager();

		Binding binding = new Binding();
		binding.setVariable("MAP", new HashMap<>());

		String script = "MAP.put(\"key\", ";
		
		logger.info("Start Infinite Loop!");
		
		int threadPoolSize = 50;
		Executor executor = Executors.newFixedThreadPool(threadPoolSize);
		for(int i = 1; i > 0; i++) {
			executor.execute(() -> {
				try {
					// 10
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					// 10
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					// 10
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					// 10
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					GroovyUtil.executeScriptWithClose(binding, script + "\"" + UUID.randomUUID() + "\");");
					
				} catch (IOException e) {
					logger.error("Some error during script execution!", e);
				}
			});
			Thread.sleep(5000);
		}
	}
}
