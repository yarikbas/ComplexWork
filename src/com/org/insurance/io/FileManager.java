package com.org.insurance.io;

import com.org.insurance.domain.Derivative;
import com.org.insurance.domain.Obligation;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileManager {

    /** Збереження деривативу у бінарний файл. */
    public void saveDerivative(Derivative derivative, String filename) {
        ensureParentDir(filename);
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
            oos.writeObject(derivative);
        } catch (IOException e) {
            throw new RuntimeException("Не вдалося зберегти файл: " + filename, e);
        }
    }

    /** Завантаження деривативу з бінарного файлу. */
    public Derivative loadDerivative(String filename) {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            Object obj = ois.readObject();
            if (obj instanceof Derivative d) {
                return d;
            }
            throw new RuntimeException("Файл не містить Derivative: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Не вдалося завантажити файл: " + filename, e);
        }
    }

    /** Експорт у читабельний текстовий файл (UTF-8). */
    public void exportToText(Derivative derivative, String filename) {
        ensureParentDir(filename);
        Path path = Path.of(filename);
        try (BufferedWriter w = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            w.write("DERIVATIVE\n");
            w.write("id: " + derivative.getId() + "\n");
            w.write("name: " + safe(derivative.getName()) + "\n");
            w.write("obligations: " + (derivative.getObligations() == null ? 0 : derivative.getObligations().size()) + "\n");
            w.write("\n");

            if (derivative.getObligations() != null) {
                int i = 1;
                for (Obligation o : derivative.getObligations()) {
                    if (o == null) continue;
                    w.write("[" + i++ + "] " + o.getClass().getSimpleName() + "\n");
                    w.write("  id            : " + o.getId() + "\n");
                    w.write("  name          : " + safe(o.getName()) + "\n");
                    w.write("  insuredAmount : " + o.getInsuredAmount() + "\n");
                    w.write("  factor        : " + o.getFactor() + "\n");
                    w.write("  period        : " + o.getPeriod() + "\n");
                    w.write("  interestRate  : " + o.getInterestRate() + "\n");
                    w.write("  probability   : " + o.getProbability() + "\n");
                    w.write("  maxCost       : " + o.getMaxCost() + "\n");
                    w.write("\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Не вдалося експортувати у текст: " + filename, e);
        }
    }

    // --- утиліти ---

    private static void ensureParentDir(String filename) {
        try {
            Path p = Path.of(filename).toAbsolutePath();
            Path parent = p.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
        } catch (IOException e) {
            throw new RuntimeException("Не вдалося створити каталог для: " + filename, e);
        }
    }

    private static String safe(String s) {
        return s == null ? "" : s;
    }
}
