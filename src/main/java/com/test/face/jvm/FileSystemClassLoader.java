package com.test.face.jvm;

import java.io.*;

/**
 * Created by cuihp on 2020/11/18.
 */
public class FileSystemClassLoader extends ClassLoader {
//    private String rootPath;
//
//    public FileSystemClassLoader(String rootPath) {
//        this.rootPath = rootPath;
//    }
//
//    @Override
//    public Class<?> loadClass(String name) throws ClassNotFoundException {
//        Class<?> loadedClass = findLoadedClass(name);
//        if (loadedClass != null) {
//            return loadedClass;
//        } else {
//            try {
//                ClassLoader parent = this.getParent();
//                Class<?> aClass = null;
//                try {
//                    aClass = parent.loadClass(name);
//                } catch (Exception e) {
//
//                }
//                if (aClass != null) {
//                    return aClass;
//                } else {
//                    byte[] classData = getClassData(name);
//                    if (classData == null) {
//                        throw new ClassCastException();
//                    } else {
//                        loadedClass = defineClass(name,classData,0,classData.length);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return loadedClass;
//        }
//    }
//
//
//    private byte[] getClassData(String className) {
//        String path = this.rootPath + "/" + className.replace(".","/")+".class";
//        InputStream in = null;
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        try {
//            in = new FileInputStream(path);
//            byte[] buffer = new byte[1024];
//            int temp = 0;
//            while ((temp = in.read(buffer)) != -1) {
//                outputStream.write(buffer,0,temp);
//            }
//            return outputStream.toByteArray();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return null;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (outputStream != null) {
//                try {
//                    outputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
    private String rootDir;

    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        }
        else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getClassData(String className) {
        String path = classNameToPath(className);
        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String classNameToPath(String className) {
        return rootDir + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
    }



}
