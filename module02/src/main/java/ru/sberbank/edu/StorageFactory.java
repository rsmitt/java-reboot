package ru.sberbank.edu;

public class StorageFactory {
    private StorageFactory() {
    }

    public static Storage getStorage(String type) {
        if (type.equals("F")) return new StorageFileImpl();
        if (type.equals("D")) return new StorageDbImpl();
        throw new AppException("Storage Impl not found");
    }
}
