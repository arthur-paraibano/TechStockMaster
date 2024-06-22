package controller;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import util.base.BaseDados;
import view.login.Loading;

public class ReportsController {
    private Connection connection;
    private final String nome = "Gerando relatório";
    private JasperPrint jasperPrint = null;
    private Loading loading;

    public void usuarioCadastrado() {
        connection = BaseDados.getConnection();
        loading = new Loading(nome);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String reportPath = "/reports/UsuárioCadastrado.jrxml";
                    InputStream jasperFile = getClass().getResourceAsStream(reportPath);
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile);
                    // Preencher o relatório com os dados do banco de dados
                    jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);
                    // Exportar o relatório para um arquivo PDF
                    JasperExportManager.exportReportToPdf(jasperPrint);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        assert connection != null;
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    loading.dispose();
                    // Configurar o JasperViewer
                    JasperViewer viewer = new JasperViewer(jasperPrint, false);
                    viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                    viewer.setAlwaysOnTop(true);
                    viewer.setVisible(true);
                }
            }
        });
        thread.start();
        loading.setVisible(true);
    }

    public void equipamentoCadastrado() {
        connection = BaseDados.getConnection();
        loading = new Loading(nome);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String reportPath = "/reports/EquipamentosCadastrados.jrxml";
                    InputStream jasperFile = getClass().getResourceAsStream(reportPath);
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile);
                    // Preencher o relatório com os dados do banco de dados
                    jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);
                    // Exportar o relatório para um arquivo PDF
                    JasperExportManager.exportReportToPdf(jasperPrint);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        assert connection != null;
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    loading.dispose();
                    // Configurar o JasperViewer
                    JasperViewer viewer = new JasperViewer(jasperPrint, false);
                    viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                    viewer.setAlwaysOnTop(true);
                    viewer.setVisible(true);
                }
            }
        });
        thread.start();
        loading.setVisible(true);
    }

    public void equipamentoTag() {
        connection = BaseDados.getConnection();
        loading = new Loading(nome);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String reportPath = "/reports/EquipamentosCTag.jrxml";
                    InputStream jasperFile = getClass().getResourceAsStream(reportPath);
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile);
                    // Preencher o relatório com os dados do banco de dados
                    jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);
                    // Exportar o relatório para um arquivo PDF
                    JasperExportManager.exportReportToPdf(jasperPrint);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        assert connection != null;
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    loading.dispose();
                    // Configurar o JasperViewer
                    JasperViewer viewer = new JasperViewer(jasperPrint, false);
                    viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                    viewer.setAlwaysOnTop(true);
                    viewer.setVisible(true);
                }
            }
        });
        thread.start();
        loading.setVisible(true);
    }

    public void setorCadastrado() {
        connection = BaseDados.getConnection();
        loading = new Loading(nome);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String reportPath = "/reports/Setor(es)Cadastrado(s).jrxml";
                    InputStream jasperFile = getClass().getResourceAsStream(reportPath);
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile);
                    // Preencher o relatório com os dados do banco de dados
                    jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);
                    // Exportar o relatório para um arquivo PDF
                    JasperExportManager.exportReportToPdf(jasperPrint);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        assert connection != null;
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    loading.dispose();
                    // Configurar o JasperViewer
                    JasperViewer viewer = new JasperViewer(jasperPrint, false);
                    viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                    viewer.setAlwaysOnTop(true);
                    viewer.setVisible(true);
                }
            }
        });
        thread.start();
        loading.setVisible(true);
    }

    public void tagCadastradas() {
        connection = BaseDados.getConnection();
        loading = new Loading(nome);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String reportPath = "/reports/Tag(s)Cadastrado(s).jrxml";
                    InputStream jasperFile = getClass().getResourceAsStream(reportPath);
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile);
                    // Preencher o relatório com os dados do banco de dados
                    jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);
                    // Exportar o relatório para um arquivo PDF
                    JasperExportManager.exportReportToPdf(jasperPrint);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        assert connection != null;
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    loading.dispose();
                    // Configurar o JasperViewer
                    JasperViewer viewer = new JasperViewer(jasperPrint, false);
                    viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                    viewer.setAlwaysOnTop(true);
                    viewer.setVisible(true);
                }
            }
        });
        thread.start();
        loading.setVisible(true);
    }

    public void estoque() {
        connection = BaseDados.getConnection();
        loading = new Loading(nome);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String reportPath = "/reports/EstoqueDeMaterial.jrxml";
                    InputStream jasperFile = getClass().getResourceAsStream(reportPath);
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile);
                    // Preencher o relatório com os dados do banco de dados
                    jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);
                    // Exportar o relatório para um arquivo PDF
                    JasperExportManager.exportReportToPdf(jasperPrint);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        assert connection != null;
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    loading.dispose();
                    // Configurar o JasperViewer
                    JasperViewer viewer = new JasperViewer(jasperPrint, false);
                    viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                    viewer.setAlwaysOnTop(true);
                    viewer.setVisible(true);
                }
            }
        });
        thread.start();
        loading.setVisible(true);
    }

    public void chamadosTI() {
        connection = BaseDados.getConnection();
        loading = new Loading(nome);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String reportPath = "/reports/ChamadosTI.jrxml";
                    InputStream jasperFile = getClass().getResourceAsStream(reportPath);
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile);
                    // Preencher o relatório com os dados do banco de dados
                    jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);
                    // Exportar o relatório para um arquivo PDF
                    JasperExportManager.exportReportToPdf(jasperPrint);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        assert connection != null;
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    loading.dispose();
                    // Configurar o JasperViewer
                    JasperViewer viewer = new JasperViewer(jasperPrint, false);
                    viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                    viewer.setAlwaysOnTop(true);
                    viewer.setVisible(true);
                }
            }
        });
        thread.start();
        loading.setVisible(true);
    }

    public void concertos() {
        connection = BaseDados.getConnection();
        loading = new Loading(nome);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String reportPath = "/reports/ConcertosEquipamentos.jrxml";
                    InputStream jasperFile = getClass().getResourceAsStream(reportPath);
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile);
                    // Preencher o relatório com os dados do banco de dados
                    jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);
                    // Exportar o relatório para um arquivo PDF
                    JasperExportManager.exportReportToPdf(jasperPrint);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        assert connection != null;
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    loading.dispose();
                    // Configurar o JasperViewer
                    JasperViewer viewer = new JasperViewer(jasperPrint, false);
                    viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                    viewer.setAlwaysOnTop(true);
                    viewer.setVisible(true);
                }
            }
        });
        thread.start();
        loading.setVisible(true);
    }

    public void solicitacaoCompras() {
        connection = BaseDados.getConnection();
        loading = new Loading(nome);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String reportPath = "/reports/SolicitaçãoDeComprasTI.jrxml";
                    InputStream jasperFile = getClass().getResourceAsStream(reportPath);
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile);
                    // Preencher o relatório com os dados do banco de dados
                    jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);
                    // Exportar o relatório para um arquivo PDF
                    JasperExportManager.exportReportToPdf(jasperPrint);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        assert connection != null;
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    loading.dispose();
                    // Configurar o JasperViewer
                    JasperViewer viewer = new JasperViewer(jasperPrint, false);
                    viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                    viewer.setAlwaysOnTop(true);
                    viewer.setVisible(true);
                }
            }
        });
        thread.start();
        loading.setVisible(true);
    }

    public void controleDeSaida() {
        connection = BaseDados.getConnection();
        loading = new Loading(nome);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String reportPath = "/reports/ControleDeSaidas.jrxml";
                    InputStream jasperFile = getClass().getResourceAsStream(reportPath);
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile);
                    // Preencher o relatório com os dados do banco de dados
                    jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);
                    // Exportar o relatório para um arquivo PDF
                    JasperExportManager.exportReportToPdf(jasperPrint);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        assert connection != null;
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    loading.dispose();
                    // Configurar o JasperViewer
                    JasperViewer viewer = new JasperViewer(jasperPrint, false);
                    viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                    viewer.setAlwaysOnTop(true);
                    viewer.setVisible(true);
                }
            }
        });
        thread.start();
        loading.setVisible(true);
    }
}
