package pl.aw84.imagelib.imageapi.service;

import java.io.File;

public class SaveFile {
    private final int dirLevels = 6;

    public String createDirTree(String dirBase, String hexDigest) {
        String relativePath = String.join("/", this.split(hexDigest));
        String dir_path = dirBase + "/" + relativePath;
        new File(dir_path).mkdirs();
        return relativePath;
    }

    public String[] split(String hexDigest) {
        if (hexDigest.length() < dirLevels * 2) {
            throw new IllegalArgumentException("Hex Digest length error");
        }
        String important_base = hexDigest.substring(0, dirLevels * 2);
        return important_base.split("(?<=\\G..)");
    }
}
