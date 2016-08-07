package com.proizvo.pkg.app;

import static com.proizvo.pkg.util.IOHelper.findExe;
import static com.proizvo.pkg.util.IOHelper.build;
import static com.proizvo.pkg.util.IOHelper.find;
import static com.proizvo.pkg.util.IOHelper.getFilePart;
import static com.proizvo.pkg.util.IOHelper.replace;
import static com.proizvo.pkg.util.JsonHelper.asMap;
import static com.proizvo.pkg.util.JsonHelper.getJsonResource;
import static com.proizvo.pkg.util.JsonHelper.getPropResource;
import static com.proizvo.pkg.util.OSHelper.detectOS;
import static com.proizvo.pkg.util.ZipHelper.unpack;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.SystemUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.proizvo.pkg.net.Download;
import com.proizvo.pkg.proc.Executer;

public class Program {

	public static void main(String[] args) throws IOException,
			InterruptedException {
		Properties cfg = getPropResource("internal/defaults.cfg");
		Map<String, String> sysVars = build("home", SystemUtils.USER_HOME);
		File workdir = new File(replace(cfg.getProperty("workdir"), sysVars));
		List<String> osKeys = detectOS();
		osKeys.add("unknown");
		JsonObject recipes = getJsonResource("internal/sources.json")
				.getAsJsonObject();
		for (Entry<String, JsonElement> e : recipes.entrySet()) {
			String key = e.getKey();
			JsonObject val = e.getValue().getAsJsonObject();

			System.out.format("Recipe '%s'... %n", key);
			Map<String, String> vars = asMap(val.get("vars"));
                        
                        Map<String, String> skip = asMap(val.get("skip"));
                        String skipFilePath = skip.get("file");
                        if (skipFilePath != null) {
                            File skipFile = findExe(workdir, skipFilePath, false);
                            if (skipFile != null && skipFile.exists()) {
                                System.out.println(" skip because '"+skipFile.getName()+"' already exists!");
                                continue;
                            }
                        }

			Map<String, String> urls = asMap(val.get("download"));
			String url = replace(find(urls, osKeys), vars);

			if (url != null) {
				Download dl = new Download(workdir, url, getFilePart(url));
				System.out.println(" * " + dl);
				dl.getLatch().await();
				unpack(workdir, dl.getFile(), true);
			}

			Map<String, String> cmds = asMap(val.get("script"));
			String cmd = replace(find(cmds, osKeys), vars);

			if (cmd != null) {
				Executer ex = new Executer(workdir, cmd);
				System.out.println(" * " + ex);
				ex.getLatch().await();
			}
		}

		System.out.println("Waiting...");
		System.in.read();
		System.out.println("Done.");
		System.exit(0);
	}
}