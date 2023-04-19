package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.data.category.DefaultCategoryDataset;

public class Main {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.119.119:1521:dink";
		String user = "scott";
		String password = "tiger";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String queryTop5 = "SELECT * FROM 행복지수 WHERE year = 2018 ORDER BY rank ASC FETCH FIRST 5 ROWS ONLY";

		String queryBottom5 = "SELECT * FROM 행복지수 WHERE year = 2018 ORDER BY rank DESC FETCH FIRST 5 ROWS ONLY";
		try {
			Class.forName(driver);
			System.out.println("jdbc driver 로딩 성공");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 연결 성공");

			stmt = conn.createStatement();
			rs = stmt.executeQuery(queryTop5);

			stmt = conn.createStatement();
			rs = stmt.executeQuery(queryTop5);

			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			while (rs.next()) {
			    String country = rs.getString(3);
			    double gdp = rs.getDouble(4);
			    double socialSupport = rs.getDouble(5);
			    double healthyLife = rs.getDouble(6);
			    double freedom = rs.getDouble(7);
			    double generosity = rs.getDouble(8);
			    double corruption = rs.getDouble(9);
			    double happinessTop = gdp + socialSupport + healthyLife + freedom + generosity - corruption;
			    dataset.addValue(happinessTop, "GDP", country);
			    dataset.addValue(socialSupport, "Social Support", country);
			    dataset.addValue(healthyLife, "Healthy Life Expectancy", country);
			    dataset.addValue(freedom, "Freedom to Make Life Choices", country);
			    dataset.addValue(generosity, "Generosity", country);
			    dataset.addValue(corruption, "Perceptions of Corruption", country);
			}


			stmt = conn.createStatement();
			rs = stmt.executeQuery(queryBottom5);
			while (rs.next()) {
			    String country = rs.getString(3);
			    double gdp = rs.getDouble(4);
			    double socialSupport = rs.getDouble(5);
			    double healthyLife = rs.getDouble(6);
			    double freedom = rs.getDouble(7);
			    double generosity = rs.getDouble(8);
			    double corruption = rs.getDouble(9);
			    double happinessTop = gdp + socialSupport + healthyLife + freedom + generosity - corruption;
			    dataset.addValue(happinessTop, "GDP", country);
			    dataset.addValue(socialSupport, "Social Support", country);
			    dataset.addValue(healthyLife, "Healthy Life Expectancy", country);
			    dataset.addValue(freedom, "Freedom to Make Life Choices", country);
			    dataset.addValue(generosity, "Generosity", country);
			    dataset.addValue(corruption, "Perceptions of Corruption", country);
			}

			// 그래프 생성
			JFreeChart chart = ChartFactory.createStackedBarChart3D("행복지수 상위 5개국과 하위 5개국 비교", "국가", "행복지수", dataset);
			chart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45); // 45도 회전

			ChartPanel chartPanel = new ChartPanel(chart);

			// 창 생성 및 그래프 추가
			JFrame frame = new JFrame("행복지수");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(chartPanel);
			frame.setSize(1000, 600); // 크기 설정
			frame.setVisible(true);

		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}