package com.org.insurance.io;

import com.org.insurance.domain.Derivative;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileManager {

    public void saveDerivative(Derivative derivative, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(derivative);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public Derivative loadDerivative(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Derivative) ois.readObject();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportToText(Derivative d, String filename) {
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            pw.println("Derivative: " + d.getName());
            for (int i = 0; i < d.getItems().size(); i++) {
                var o = d.getItems().get(i);
                pw.println("- " + o.getName()
                        + " | risk=" + o.calculateRisk()
                        + " | value=" + o.calculateValue()
                        + " | payout=" + o.calculatePayout());
            }
            pw.println("TOTAL: risk=" + d.getTotalRisk() + " | value=" + d.getTotalValue());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
