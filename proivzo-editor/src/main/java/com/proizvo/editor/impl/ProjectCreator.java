package com.proizvo.editor.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMDocumentType;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.helger.css.decl.CSSDeclaration;
import com.helger.css.decl.CSSExpression;
import com.helger.css.decl.CSSFontFaceRule;
import com.helger.css.decl.CascadingStyleSheet;
import com.helger.css.writer.CSSWriter;
import com.proizvo.editor.api.IProject;
import com.proizvo.editor.data.Armor;
import com.proizvo.editor.data.Armors;
import com.proizvo.editor.data.Bgm;
import com.proizvo.editor.data.Map;
import com.proizvo.editor.data.MapInfo;
import com.proizvo.editor.data.MapInfos;
import com.proizvo.editor.data.Trait;
import com.proizvo.editor.util.Files;

import static com.proizvo.editor.util.Lists.*;

public class ProjectCreator {

    private static final Locale locale;
    private static final ResourceBundle texts;

    static {
        locale = new Locale("de", "DE");
        texts = ResourceBundle.getBundle("TextBundle", locale);
    }

    public static IProject createNew(String gameTitle, String projectName,
            String storageLocation) {
        try {
            String templ = "template/";
            String tcode = templ + "code/";
            File projDir = new File(storageLocation, projectName);
            projDir.mkdir();
            String encoding = "UTF8";
            File gpf = new File(projDir, "Game.rpgproject");
            FileUtils.write(gpf, "RPGMV 1.0.0", encoding);
            File idx = new File(projDir, "index.html");
            writeIndex(idx, gameTitle);
            File audio = Files.mkdir(projDir, "audio");
            File bgm = Files.mkdir(audio, "bgm");
            File bgs = Files.mkdir(audio, "bgs");
            File me = Files.mkdir(audio, "me");
            File se = Files.mkdir(audio, "se");
            File data = Files.mkdir(projDir, "data");
            newJson(insert(new MapInfos(), null, new MapInfo(1, false, "MAP001", 1, 0,
                    411.5, 334)), new File(data, "MapInfos.json"));
            newJson(newStdMap(), new File(data, "Map001.json"));
            newJson(newArmors(), new File(data, "Armors.json"));
            File fonts = Files.mkdir(projDir, "fonts");
            writeFontsCSS(new File(fonts, "gamefont.css"));
            copyRes(templ + "game.ttf", new File(fonts, "mplus-1m-regular.ttf"));
            File icon = Files.mkdir(projDir, "icon");
            copyRes(templ + "proj_icon.png", new File(icon, "icon.png"));
            File img = Files.mkdir(projDir, "img");
            File animations = Files.mkdir(img, "animations");
            File battlebacks1 = Files.mkdir(img, "battlebacks1");
            File battlebacks2 = Files.mkdir(img, "battlebacks2");
            File characters = Files.mkdir(img, "characters");
            File enemies = Files.mkdir(img, "enemies");
            File faces = Files.mkdir(img, "faces");
            File parallaxes = Files.mkdir(img, "parallaxes");
            File pictures = Files.mkdir(img, "pictures");
            File sv_actors = Files.mkdir(img, "sv_actors");
            File sv_enemies = Files.mkdir(img, "sv_enemies");
            File system = Files.mkdir(img, "system");
            File tilesets = Files.mkdir(img, "tilesets");
            File titles1 = Files.mkdir(img, "titles1");
            File titles2 = Files.mkdir(img, "titles2");
            File js = Files.mkdir(projDir, "js");
            copyRes(tcode + "main.js", new File(js, "main.js"));
            copyRes(tcode + "plugins.js", new File(js, "plugins.js"));
            copyRes(tcode + "rpg_core.js", new File(js, "rpg_core.js"));
            copyRes(tcode + "rpg_managers.js", new File(js, "rpg_managers.js"));
            copyRes(tcode + "rpg_objects.js", new File(js, "rpg_objects.js"));
            copyRes(tcode + "rpg_scenes.js", new File(js, "rpg_scenes.js"));
            copyRes(tcode + "rpg_sprites.js", new File(js, "rpg_sprites.js"));
            copyRes(tcode + "rpg_windows.js", new File(js, "rpg_windows.js"));
            File libs = Files.mkdir(js, "libs");
            copyRes(templ + "pixi.min.js", new File(libs, "pixi.js"));
            copyRes(templ + "lz-string.min.js", new File(libs, "lz-string.js"));
            copyRes(templ + "fpsmeter.min.js", new File(libs, "fpsmeter.js"));
            File plugins = Files.mkdir(js, "plugins");
            File movies = Files.mkdir(projDir, "movies");
            return new RPGMakerMVProj(gpf);
        } catch (IOException e) {
            throw new RuntimeException("Could not create project!", e);
        }
    }

    private static Armors newArmors() {
        Armors as = new Armors();
        as.add(null);
        Armor a = new Armor();
        a.setId(1);
        a.setName(texts.getString("armor1name"));
        a.setAtypeId(5);
        a.setDescription("");
        a.setEtypeId(2);
        a.setIconIndex(128);
        a.setNote("");
        a.setParams(new int[]{0, 0, 0, 10, 0, 0, 0, 0});
        a.setPrice(300);
        a.setTraits(new Trait(22, 1, 0));
        as.add(a);
        a = new Armor();
        a.setId(2);
        a.setName(texts.getString("armor2name"));
        a.setAtypeId(1);
        a.setDescription("");
        a.setEtypeId(3);
        a.setTraits(new Trait(22, 1, 0));
        a.setIconIndex(130);
        a.setNote("");
        a.setParams(new int[]{0, 0, 0, 10, 0, 0, 0, 0});
        a.setPrice(300);
        as.add(a);
        a = new Armor();
        a.setId(3);
        a.setName(texts.getString("armor3name"));
        a.setAtypeId(1);
        a.setDescription("");
        a.setEtypeId(4);
        a.setTraits(new Trait(22, 1, 0));
        a.setIconIndex(135);
        a.setNote("");
        a.setParams(new int[]{0, 0, 0, 10, 0, 0, 0, 0});
        a.setPrice(300);
        as.add(a);
        a = new Armor();
        a.setId(4);
        a.setName(texts.getString("armor4name"));
        a.setAtypeId(1);
        a.setDescription("");
        a.setEtypeId(5);
        a.setTraits(new Trait(22, 1, 0));
        a.setIconIndex(145);
        a.setNote("");
        a.setParams(new int[]{0, 0, 0, 10, 0, 0, 0, 0});
        a.setPrice(300);
        as.add(a);
        return as;
    }

    private static Map newStdMap() {
        Map map = new Map();
        map.setBattleback1Name("");
        map.setBattleback2Name("");
        map.setEvents(new String[]{null, null});
        map.setBgm(new Bgm("", 0, 100, 90));
        map.setBgs(new Bgm("", 0, 100, 90));
        map.setDisplayName("");
        map.setEncounterList(new String[0]);
        map.setEncounterStep(30);
        map.setNote("");
        map.setParallaxName("");
        map.setParallaxShow(true);
        map.setTilesetId(1);
        int h, w;
        map.setHeight(h = 13);
        map.setWidth(w = 17);
        int[] data = new int[(h * w) * 6];
        for (int i = 0; i < (h * w); i++) {
            data[i] = 2816;
        }
        for (int i = 0; i < ((h * w) * 5); i++) {
            data[(h * w) + i] = 0;
        }
        map.setData(data);
        return map;
    }

    private static void write(Document doc, File file) {
        try (FileWriter out = new FileWriter(file)) {
            OutputFormat fmt = OutputFormat.createPrettyPrint();
            fmt.setXHTML(true);
            fmt.setSuppressDeclaration(true);
            fmt.setExpandEmptyElements(true);
            fmt.setIndentSize(4);
            XMLWriter xml = new XMLWriter(out, fmt);
            xml.write(doc);
            xml.flush();
            xml.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not write document!", e);
        }
    }

    private static void writeIndex(File idx, String gameTitle) {
        Document doc = DocumentHelper.createDocument();
        doc.setDocType(new DOMDocumentType("html", null));
        Element root = doc.addElement("html");
        Element head = root.addElement("head");
        addMeta(head, "UTF-8");
        addMeta(head, "apple-mobile-web-app-capable", "yes");
        addMeta(head, "apple-mobile-web-app-status-bar-style",
                "black-translucent");
        addMeta(head, "viewport", "user-scalable=no");
        addLink(head, "icon", "icon/icon.png", "image/png");
        addLink(head, "apple-touch-icon", "icon/icon.png", null);
        addLink(head, "stylesheet", "fonts/gamefont.css", "text/css");
        head.addElement("title").setText(gameTitle);
        Element body = root.addElement("body");
        body.addAttribute("style", "background-color: black");
        for (String jsPath : Arrays.asList("js/libs/pixi.js",
                "js/libs/fpsmeter.js", "js/libs/lz-string.js",
                "js/rpg_core.js", "js/rpg_managers.js", "js/rpg_objects.js",
                "js/rpg_scenes.js", "js/rpg_sprites.js", "js/rpg_windows.js",
                "js/plugins.js", "js/main.js")) {
            addScript(body, jsPath);
        }
        write(doc, idx);
    }

    private static Element addLink(Element root, String rel, String href,
            String type) {
        Element link = root.addElement("link");
        link.addAttribute("rel", rel);
        link.addAttribute("href", href);
        link.addAttribute("type", type);
        return link;
    }

    private static Element addMeta(Element root, String key, String value) {
        Element meta = root.addElement("meta");
        meta.addAttribute("name", key);
        meta.addAttribute("content", value);
        return meta;
    }

    private static Element addMeta(Element root, String charset) {
        Element meta = root.addElement("meta");
        meta.addAttribute("charset", charset);
        return meta;
    }

    private static Element addScript(Element root, String path) {
        Element script = root.addElement("script");
        script.addAttribute("type", "text/javascript");
        script.addAttribute("src", path);
        return script;
    }

    private static void copyRes(String src, File dest) {
        copyRes(ProjectCreator.class, src, dest);
    }

    private static void copyRes(Class<?> clazz, String src, File dest) {
        copyRes(clazz.getClassLoader(), src, dest);
    }

    private static void copyRes(ClassLoader loader, String src, File dest) {
        try (InputStream in = loader.getResourceAsStream(src)) {
            try (FileOutputStream out = new FileOutputStream(dest)) {
                IOUtils.copy(in, out);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read resource!", e);
        }
    }

    private static void writeFontsCSS(File dest) throws IOException {
        CascadingStyleSheet css = new CascadingStyleSheet();
        CSSFontFaceRule ffr = new CSSFontFaceRule();
        ffr.addDeclaration(new CSSDeclaration("font-family",
                CSSExpression.createSimple("GameFont")));
        ffr.addDeclaration(new CSSDeclaration("src",
                CSSExpression.createURI("mplus-1m-regular.ttf")));
        css.addRule(ffr);
        CSSWriter writer = new CSSWriter();
        try (FileWriter out = new FileWriter(dest)) {
            writer.writeCSS(css, out);
        }
    }

    private static void newJson(Object obj, File file) throws IOException {
        Gson json = (new GsonBuilder())/*.setPrettyPrinting()*/.create();
        FileUtils.write(file, json.toJson(obj), "UTF-8");
    }
}
