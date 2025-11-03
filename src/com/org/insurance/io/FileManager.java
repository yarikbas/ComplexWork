package com.org.insurance.io;

import com.org.insurance.domain.Derivative;
import lombok.Data;
import java.io.*;
import java.util.List;

@Data
public class FileManager {

    public void saveToFile(List<Derivative> derivatives, String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(derivatives);
            System.out.println("Data saved to: " + path);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Derivative> loadFromFile(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            List<Derivative> derivatives = (List<Derivative>) ois.readObject();
            System.out.println("Data loaded from: " + path);
            return derivatives;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading file: " + e.getMessage());
            return null;
        }
    }
}