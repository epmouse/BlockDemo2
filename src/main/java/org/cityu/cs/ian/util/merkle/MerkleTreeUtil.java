package org.cityu.cs.ian.util.merkle;


import org.cityu.cs.ian.util.SHA256;

import java.util.List;

public class MerkleTreeUtil {

    public static String getRoot(List<String> list) {
        if (list != null && list.size() > 0) {
            list.forEach(s -> SHA256.getSHA256StrJava(s));
            MerkleTree merkleTree = new MerkleTree(list);
            byte[] serialize = merkleTree.serialize();
            return SHA256.getSHA256StrJava(new String(serialize));
        }
        return "";
    }

}
