package main;

import model.Dispositivo;
import persistencia.FakeRegistroCostos;
import ui.MonitorGerente;
import ui.MonitorPedido;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MonitorGerente monitorGerente = new MonitorGerente();
        MonitorPedido monitorPedido = new MonitorPedido(new Dispositivo(new FakeRegistroCostos(), List.of(monitorGerente)));
    }
}
