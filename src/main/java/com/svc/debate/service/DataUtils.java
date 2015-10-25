/*
package com.svc.debate.service;

*/
/**
 * Created by dsawla on 10/24/2015.
 *//*

import com.spencerdo.bitraac.algorithm.WLog;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.Order;
import com.xeiam.xchange.dto.marketdata.Trade;
import com.xeiam.xchange.dto.trade.LimitOrder;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
?
*/
/**
 * @author doyonghoon
 *//*

public class DataUtils {
    ?
    private static Connection mPostgresDatabaseConnection = null;
    ?
    public static List<LocalOrder> getLocalOpenOrders() {
        List<LocalOrder> result = new ArrayList<LocalOrder>();
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM local_order WHERE time >= now()::date + interval '2h' ORDER BY time");
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                Date timestamp = rs.getTimestamp("time");
                String algorithmName = rs.getString("algorithm");
                double algorithmRate = rs.getDouble("algorithm_rate");
                Order.OrderType orderType = Order.OrderType.valueOf(rs.getString("order_type").toUpperCase());
                BigDecimal price = rs.getBigDecimal("price");
                BigDecimal amount = rs.getBigDecimal("amount");
                BigDecimal usd = rs.getBigDecimal("current_usd");
                BigDecimal btc = rs.getBigDecimal("current_btc");
                ?
                LimitOrder limitOrder = new LimitOrder.Builder(orderType, CurrencyPair.BTC_USD)
                        .id(String.valueOf(orderId))
                        .timestamp(timestamp)
                        .limitPrice(price)
                        .tradableAmount(amount)
                        .build();
                LocalOrder o = new LocalOrder(limitOrder, algorithmRate, algorithmName);
                o.setCurrentBtcBalance(btc);
                o.setCurrentUsdBalance(usd);
                result.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    ?
    public static BigDecimal getAverageBidOrders() {
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select AVG(price) from bid_order");
            if (rs.next()) {
                if (rs.getBigDecimal(1) != null) {
                    return rs.getBigDecimal(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }
    ?
    public static List<LocalOrder> getBidOrders() {
        List<LocalOrder> result = new ArrayList<LocalOrder>();
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bid_order WHERE time >= now()::date + interval '2h' ORDER BY time");
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                Date timestamp = rs.getTimestamp("time");
                String algorithmName = rs.getString("algorithm");
                double algorithmRate = rs.getDouble("algorithm_rate");
                Order.OrderType orderType = Order.OrderType.valueOf(rs.getString("order_type").toUpperCase());
                BigDecimal price = rs.getBigDecimal("price");
                BigDecimal amount = rs.getBigDecimal("amount");
                BigDecimal usd = rs.getBigDecimal("current_usd");
                BigDecimal btc = rs.getBigDecimal("current_btc");
                ?
                LimitOrder limitOrder = new LimitOrder.Builder(orderType, CurrencyPair.BTC_USD)
                        .id(String.valueOf(orderId))
                        .timestamp(timestamp)
                        .limitPrice(price)
                        .tradableAmount(amount)
                        .build();
                LocalOrder o = new LocalOrder(limitOrder, algorithmRate, algorithmName);
                o.setCurrentBtcBalance(btc);
                o.setCurrentUsdBalance(usd);
                result.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    ?
    public static int removeTradeAfterThreeHours() {
        try {
            Statement stmt = getConnection().createStatement();
            return stmt.executeUpdate("delete from bitstamp_trade where time < now()::date - interval '3h'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    ?
    public static boolean removeLocalOrder(String orderId) {
        try {
            PreparedStatement s = getConnection().prepareCall("delete FROM local_order WHERE order_id=?");
            s.setInt(1, Integer.valueOf(orderId));
            int result = s.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    ?
    public static LocalOrder getLocalOrder(int orderId) {
        try {
            PreparedStatement s = getConnection().prepareCall("SELECT * FROM local_order WHERE order_id=?");
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                Date timestamp = rs.getTimestamp("time");
                String algorithmName = rs.getString("algorithm");
                BigDecimal algorithmRate = rs.getBigDecimal("algorithm_rate");
                Order.OrderType orderType = Order.OrderType.valueOf(rs.getString("order_type").toUpperCase());
                BigDecimal price = rs.getBigDecimal("price");
                BigDecimal amount = rs.getBigDecimal("amount");
                LimitOrder limitOrder = new LimitOrder.Builder(orderType, CurrencyPair.BTC_USD)
                        .id(String.valueOf(orderId))
                        .timestamp(timestamp)
                        .limitPrice(price)
                        .tradableAmount(amount)
                        .build();
                return new LocalOrder(limitOrder, algorithmRate.doubleValue(), algorithmName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    ?
    public static boolean addLocalOpenOrder(LocalOrder order) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(order.getLimitOrder().getTimestamp());
            c.set(Calendar.MILLISECOND, 0);
            WLog.i("ppo: " + order.getAlgorithmRate());
            final String query = "INSERT INTO local_order (order_id, time, algorithm, algorithm_rate, order_type, price, amount, current_usd, current_btc) SELECT ?, ?, ?, ?, ?, ?, ?, ?, ? "
                    + "WHERE NOT EXISTS "
                    + "(SELECT algorithm, algorithm_rate, order_type, price, amount, current_usd, current_btc FROM local_order WHERE algorithm=? and algorithm_rate=? and order_type=? and price=? and amount=?)";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(order.getLimitOrder().getId()));
            stmt.setTimestamp(2, new Timestamp(c.getTimeInMillis()));
            stmt.setString(3, order.getAlgorithmName());
            stmt.setBigDecimal(4, new BigDecimal(String.valueOf(order.getAlgorithmRate())));
            stmt.setString(5, order.getLimitOrder().getType().name());
            stmt.setBigDecimal(6, order.getLimitOrder().getLimitPrice());
            stmt.setBigDecimal(7, order.getLimitOrder().getTradableAmount());
            stmt.setBigDecimal(8, order.getCurrentUsdBalance());
            stmt.setBigDecimal(9, order.getCurrentBtcBalance());
            stmt.setString(10, order.getAlgorithmName());
            stmt.setBigDecimal(11, new BigDecimal(String.valueOf(order.getAlgorithmRate())));
            stmt.setString(12, order.getLimitOrder().getType().name());
            stmt.setBigDecimal(13, order.getLimitOrder().getLimitPrice());
            stmt.setBigDecimal(14, order.getLimitOrder().getTradableAmount());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    ?
    public static boolean addBidOpenOrder(LocalOrder order) {
        try {
            if (order.getLimitOrder().getType() == Order.OrderType.BID) {
                Calendar c = Calendar.getInstance();
                c.setTime(order.getLimitOrder().getTimestamp());
                c.set(Calendar.MILLISECOND, 0);
                final String query = "INSERT INTO bid_order (order_id, time, algorithm, algorithm_rate, order_type, price, amount, current_usd, current_btc) SELECT ?, ?, ?, ?, ?, ?, ?, ?, ? "
                        + "WHERE NOT EXISTS "
                        + "(SELECT algorithm, algorithm_rate, order_type, price, amount, current_usd, current_btc FROM bid_order WHERE algorithm=? and algorithm_rate=? and order_type=? and price=? and amount=?)";
                PreparedStatement stmt = getConnection().prepareStatement(query);
                stmt.setInt(1, Integer.parseInt(order.getLimitOrder().getId()));
                stmt.setTimestamp(2, new Timestamp(c.getTimeInMillis()));
                stmt.setString(3, order.getAlgorithmName());
                stmt.setBigDecimal(4, new BigDecimal(String.valueOf(order.getAlgorithmRate())));
                stmt.setString(5, order.getLimitOrder().getType().name());
                stmt.setBigDecimal(6, order.getLimitOrder().getLimitPrice());
                stmt.setBigDecimal(7, order.getLimitOrder().getTradableAmount());
                stmt.setBigDecimal(8, order.getCurrentUsdBalance());
                stmt.setBigDecimal(9, order.getCurrentBtcBalance());
                stmt.setString(10, order.getAlgorithmName());
                stmt.setBigDecimal(11, new BigDecimal(String.valueOf(order.getAlgorithmRate())));
                stmt.setString(12, order.getLimitOrder().getType().name());
                stmt.setBigDecimal(13, order.getLimitOrder().getLimitPrice());
                stmt.setBigDecimal(14, order.getLimitOrder().getTradableAmount());
                int result = stmt.executeUpdate();
                return result > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    ?
    public static boolean removeBidOrderLastRow() {
        try {
            final String query = "DELETE FROM bid_order WHERE price IN (SELECT MIN(price) FROM bid_order LIMIT 1)";
            Statement stmt = getConnection().createStatement();
            int result = stmt.executeUpdate(query);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    ?
    public static List<Trade> getLocalTrades() {
        List<Trade> result = new ArrayList<Trade>();
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select tid, time, price, amount from bitstamp_trade where time >= now()::date - interval 'h2' order by tid");
            while (rs.next()) {
                BigDecimal price = rs.getBigDecimal("price");
                Date time = rs.getTimestamp("time");
                Trade trade = new Trade(null, rs.getBigDecimal("amount"), CurrencyPair.BTC_USD, price, time, String.valueOf(rs.getInt("tid")));
                result.add(trade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    ?
    public static List<Trade> addPreviousTrades(List<Trade> trades) {
        List<Trade> addedTrades = new ArrayList<Trade>();
        try {
            for (Trade t : trades) {
                Calendar c = Calendar.getInstance();
                c.setTime(t.getTimestamp());
                c.set(Calendar.MILLISECOND, 0);
                final String query = "INSERT INTO bitstamp_trade(tid, time, price, amount) SELECT ?, ?, ?, ? WHERE NOT EXISTS(SELECT tid FROM bitstamp_trade WHERE tid=?)";
                PreparedStatement stmt = getConnection().prepareStatement(query);
                stmt.setInt(1, Integer.valueOf(t.getId()));
                stmt.setTimestamp(2, new Timestamp(c.getTimeInMillis()));
                stmt.setBigDecimal(3, t.getPrice());
                stmt.setBigDecimal(4, t.getTradableAmount());
                stmt.setInt(5, Integer.valueOf(t.getId()));
                int result = stmt.executeUpdate();
                if (result > 0) {
                    //WLog.i("added past trade: " + result);
                    addedTrades.add(t);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addedTrades;
    }
    ?
    public static Trade getLastLocalTrade() {
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT tid, time, price, amount FROM bitstamp_trade ORDER BY tid DESC LIMIT 1");
            while (rs.next()) {
                BigDecimal price = rs.getBigDecimal("price");
                Date time = rs.getTimestamp("time");
                return new Trade(null, rs.getBigDecimal("amount"), CurrencyPair.BTC_USD, price, time, String.valueOf(rs.getInt("tid")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    ?
    private static void createTradeTableIfNotExists() {
        try {
            Statement stmt = getConnection().createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS bitstamp_trade (tid INT PRIMARY KEY NOT NULL, time TIMESTAMP, price DECIMAL, amount DECIMAL)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ?
    private static void createLocalOrderTableIfNotExists() {
        try {
            Statement stmt = getConnection().createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS local_order (order_id INT, time TIMESTAMP, algorithm TEXT, algorithm_rate DECIMAL, order_type TEXT, price DECIMAL, amount DECIMAL, current_usd DECIMAL, current_btc DECIMAL)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ?
    private static void createBidOrderTableIfNotExists() {
        try {
            Statement stmt = getConnection().createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS bid_order (order_id INT, time TIMESTAMP, algorithm TEXT, algorithm_rate DECIMAL, order_type TEXT, price DECIMAL, amount DECIMAL, current_usd DECIMAL, current_btc DECIMAL)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ?
    public static Connection getConnection() {
        if (mPostgresDatabaseConnection == null) {
            try {
                URI dbUri = new URI(System.getenv("DATABASE_URL"));
                String username = dbUri.getUserInfo().split(":")[0];
                String password = dbUri.getUserInfo().split(":")[1];
                String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
                mPostgresDatabaseConnection = DriverManager.getConnection(dbUrl, username, password);
                createTradeTableIfNotExists();
                createLocalOrderTableIfNotExists();
                createBidOrderTableIfNotExists();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mPostgresDatabaseConnection;
    }
}*/
