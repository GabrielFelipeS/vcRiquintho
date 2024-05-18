package br.com.ifsp.vcRiquinho.conta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ifsp.vcRiquinho.base.interfaces.DAO;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;

public class ContaDAO implements DAO<DTOConta, Integer> {
	private Connection conn;

	public ContaDAO(Connection conn) {
		this.conn = conn;
	}

	private DTOConta createDTOConta(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("id");
		String documentoTitular = rs.getString("documento_titular");
		Double montanteFinanceiro = rs.getDouble("montante_financeiro");
		Integer id_produto = rs.getInt("id_produto_conta");
		Double cdi = rs.getDouble("cdi");
		String tipo_conta = rs.getString("tipo_conta");
		
		return new DTOConta(id, documentoTitular, montanteFinanceiro, id_produto, cdi, tipo_conta);
	}

	@Override
	public List<DTOConta> findAll() {
		return findWhere("1 = 1");
	}

	@Override
	public List<DTOConta> findWhere(String where) {
		List<DTOConta> list = new ArrayList<DTOConta>();

		try (Statement st = conn.createStatement()) {
			st.execute("SELECT * FROM conta WHERE " + where);
			try (ResultSet rs = st.getResultSet()) {

				while (rs.next()) {
					DTOConta dto = createDTOConta(rs);
					list.add(dto);
				}
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public DTOConta insert(DTOConta dto) {
		try (PreparedStatement pst = conn.prepareStatement(
				"INSERT INTO conta (documento_titular, montante_financeiro, id_produto_conta, cdi, tipo_conta) "
						+ "VALUES (?, ?, ?, ?, CAST(? as TIPO_CONTA))", Statement.RETURN_GENERATED_KEYS)) {
			pst.setString(1, dto.documentoTitular());
			pst.setDouble(2, dto.montanteFinanceiro());
			pst.setInt(3, dto.id_produto());
			pst.setDouble(4, dto.cdi());
			pst.setObject(5, dto.tipo_conta(), java.sql.Types.OTHER);

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Falha na criação da conta, nenhuma linha afetada.");
			}

			try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int idGerado = generatedKeys.getInt("id");
					return findBy(idGerado);
				} else {
					throw new SQLException("Falha na criação da conta, nenhum ID obtido.");
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public Boolean deleteBy(Integer id) {

		try (PreparedStatement pst = conn.prepareStatement("UPDATE conta SET ativo = false WHERE id = ? ")) {
			pst.setInt(1, id);
			int affectedRows = pst.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Falha ao deletar conta, nenhuma linha afetada.");
			}

			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public DTOConta findBy(Integer id) {

		try (PreparedStatement pst = conn.prepareStatement("SELECT * FROM conta WHERE id = ? ")) {
			pst.setInt(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					DTOConta dto = createDTOConta(rs);
					return dto;
				}
				throw new SQLException("Falha na busca de contas, nenhuma conta encontrada.");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public DTOConta updateBy(DTOConta dto) {
		try (PreparedStatement pst = conn
				.prepareStatement("UPDATE conta SET " + "id_produto_conta = ?, cdi = ? WHERE id = ? ")) {

			pst.setInt(1, dto.id_produto());
			pst.setDouble(2, dto.cdi());
			pst.setInt(3, dto.id());

			int affectedRows = pst.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Falha na atualização da conta, nenhuma linha afetada.");
			}
			
			return findBy(dto.id());
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

}
