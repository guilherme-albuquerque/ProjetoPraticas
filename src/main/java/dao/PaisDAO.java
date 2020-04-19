package dao;

import jdk.internal.access.JavaIOFileDescriptorAccess;
import model.Pais;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaisDAO extends Pais{
    public int criar(Pais pais) {
        String sqlInsert = "INSERT INTO pais(nome, populacao, area) VALUES (?, ?, ?)";
        // usando o try with resources do Java 7, que fecha o que abriu
        try (Connection conn = ConnectionFactory.obtemConexao();
             PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
            stm.setString(1, pais.getNome());
            stm.setString(2, String.valueOf(pais.getPopulacao()));
            stm.setString(3, pais.getArea());
            stm.execute();
            String sqlQuery = "SELECT LAST_INSERT_ID()";
            try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
                 ResultSet rs = stm2.executeQuery();) {
                if (rs.next()) {
                    pais.setId(rs.getInt(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pais.getId();
    }

    public void atualizar(Pais pais) {
        String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
        // usando o try with resources do Java 7, que fecha o que abriu
        try (Connection conn = ConnectionFactory.obtemConexao();
             PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
            stm.setString(1, pais.getNome());
            stm.setString(2, String.valueOf(pais.getPopulacao()));
            stm.setString(3, pais.getArea());
            stm.setInt(4, pais.getId());
            stm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sqlDelete = "DELETE FROM pais WHERE id = ?";
        // usando o try with resources do Java 7, que fecha o que abriu
        try (Connection conn = ConnectionFactory.obtemConexao();
             PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
            stm.setInt(1, id);
            stm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Pais carregar(int id) {
        Pais pais = new Pais();
        pais.setId(id);
        String sqlSelect = "SELECT nome, populacao, area FROM pais WHERE pais.id = ?";
        // usando o try with resources do Java 7, que fecha o que abriu
        try (Connection conn = ConnectionFactory.obtemConexao();
             PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
            stm.setInt(1, pais.getId());
            try (ResultSet rs = stm.executeQuery();) {
                if (rs.next()) {
                    pais.setNome(rs.getString("nome"));
                    pais.setPopulacao(Long.parseLong(rs.getString("populacao")));
                    pais.setArea(Double.parseDouble(rs.getString("area")));
                } else {
                    pais.setId(-1);
                    pais.setNome(null);
                    pais.setPopulacao(null);
                    pais.setArea(null);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e1) {
            System.out.print(e1.getStackTrace());
        }
        return pais;
    }

    public void maiorpopulacao() throws SQLException {
        long popula = 0;String nome = null;
        for(Pais populacao : lista) {
            if(popula<populacao.getPopulacao()) {
                popula=populacao.getPopulacao();
                nome = populacao.getNome();
                this.setNome(nome);
                this.setPopulacao(popula);

            }
        }
        System.out.println("Pais: "+nome+", Popupacao: "+popula);
    }

    public void menorArea()  {
        String nome=null; double area=0;

        for(Pais pais : lista) {

            if(area ==0 ||area>pais.getArea()) {
                area= pais.getArea(); nome=pais.getNome();
                this.setNome(nome);;
                this.setArea(area);
            }
        }
        System.out.println("Pais: "+nome+", Area: "+area);
    }

    public void vetorTresPaises() {
        int i=0;
        for( i=0;i<=2;i++) {
            JavaIOFileDescriptorAccess lista;
            System.out.println("Pais:"+lista.get(i).getNome());
        }
    }


}
}
