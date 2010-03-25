var JAKE = require("jake");
var FILE = require("file");
var OS = require("os");
var SYSTEM = require("system");
var FileList = require("jake/filelist").FileList;

var catalog = require("packages").catalog;

var PACKAGES = ["jack"];
var BUILD = FILE.path(module.path).dirname().join("build");

var ant = ["ant", "-Dsdk.dir="+getAppEngineSDKPath()];

JAKE.task("default", "runserver");

JAKE.task("runserver", ["assemble"], function() {
    OS.system("cd build && " + ant.concat("runserver").map(OS.enquote).join(" "));
});

JAKE.task("update", ["assemble"], function() {
    OS.system("cd build && " + ant.concat("update").map(OS.enquote).join(" "));
});

JAKE.task("assemble", function() {
    if (FILE.exists(BUILD))
        FILE.rmtree(BUILD);
    
    FILE.mkdirs(BUILD);
    
    var selfPkg = {
        directory : FILE.join(FILE.cwd(),""),
        lean : {
            exclude : [
                "**/.DS_Store",
                "**/.git**",
                "**/.svn**",
                "build/**/*",
                "src/**/*",
                "war/**/*",
                "Jakefile",
                "README*",
                "package.json"
            ]
        }
    }
    
    // jack-servlet
    leanInstall(catalog["jack-servlet"], BUILD);
    
    // narwhal
    var narwhalDest = FILE.join(BUILD, "war", "WEB-INF", "narwhal");
    leanInstall(catalog["narwhal"], narwhalDest);
    
    var packagesDest = FILE.join(narwhalDest, "packages");
    FILE.mkdirs(packagesDest);
    
    // packages
    PACKAGES.forEach(function(name) {
        if (!catalog[name])
            throw new Error("Can't find package: " + name);
        
        leanInstall(catalog[name], FILE.join(packagesDest, name))
    });
    
    // self
    leanInstall(selfPkg, FILE.join(BUILD, "war", "WEB-INF"));
    
    var libDest = FILE.join(BUILD, "war", "WEB-INF", "lib");
    FILE.mkdirs(libDest);
    
    // TODO: copy all jars of installed packages
    // copy rhino's js.jar to lib directory
    FILE.copy(
        FILE.join(catalog["narwhal"].directory, "engines", "rhino", "jars", "js.jar"),
        FILE.join(libDest, "js.jar")
    );
});

function copyFileList(list, srcBase, destBase) {
    list.forEach(function(src) {
        var rel = FILE.relative(srcBase, src);
        var dest = FILE.join(destBase, rel);
        
        if (FILE.isDirectory(src)) {
            FILE.mkdirs(dest);
        } else {
            print(src + " => " + dest);
            FILE.mkdirs(FILE.dirname(dest));
            FILE.copy(src, dest);
        }
    });
}

function leanInstall(pkg, dest) {
    var list = buildLeanList(pkg);
    var src = pkg.directory;
    copyFileList(list, src, dest);
}

function buildLeanList(pkg) {
    var list = new FileList();
    
    var packagePath = pkg.directory;
    
    // default (include everything except VCS dirs, tests, examples, docs, bin?)
    var lean = pkg.lean || {
        include : [
            FILE.join("**", "*")
        ],
        exclude : [
            "**/.git**",
            "**/.svn**",
            "**/.DS_Store",
            // "bin/**/*",
            "tests/**/*",
            "examples/**/*",
            "docs/**/*"
        ]
    };
    
    // default to everything
    if (lean.exclude && (!lean.include || lean.include.length === 0))
        lean.include = ["**/*"];
    
    if (Array.isArray(lean)) {
        lean.forEach(function(include) {
            list.include(FILE.join(packagePath, include, "**", "*"));
        });
    } else {
        if (lean.include) {
            lean.include.map(function(include) {
                var inc = FILE.join(packagePath, include);
                // print(inc)
                list.include(inc);
            });
        }
        if (lean.exclude) {
            lean.exclude.map(function(exclude) {
                var exc = FILE.join(packagePath, exclude);
                // print(exc)
                list.exclude(exc);
            });
        }
    }
    
    return list;
}    

function getAppEngineSDKPath() {
    if (catalog["appengine-java-sdk"])
        return catalog["appengine-java-sdk"].directory;
    if (SYSTEM.env["APPENGINE_JAVA_SDK"])
        return SYSTEM.env["APPENGINE_JAVA_SDK"];
    throw new Error("'appengine-java-sdk' and APPENGINE_JAVA_SDK environment variable not set.");
};
