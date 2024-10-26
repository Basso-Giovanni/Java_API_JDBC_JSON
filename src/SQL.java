//PRIMA DI FARE QUALSIASI COSA E' NECESSARIO INCLUDERE LA DIPENDENZA MAVEN DI MYSQL
//INOLTRE PER USARLO SERVE UN DATABASE APERTO CON XAMPP

import java.sql.*;

public class SQL
{
    // Rappresenta una connessione a un database
    private Connection con;

    /** Costruttore parametrizzato per creare la connessione
     *
     * @param address indirizzo IP del database
     * @param port porta di connessione TCP
     * @param db_name nome del database
     * @param user username per accedere
     * @param pass password per accedere
     * @throws SQLException se si verifica un problema con la connessione
     */
    public SQL(String address, String port, String db_name, String user, String pass) throws SQLException
    {
        String connection = "jdbc:mysql://" + address + ":" + port + "/" + db_name;
        try
        {
            con = DriverManager.getConnection(connection, user, pass);
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
    }

    /** Costruttore di default per creare la connessione
     *
     * @param db_name nome del database
     * @throws SQLException se si verifica un problema con la connessione
     */
    public SQL(String db_name) throws SQLException
    {
        String connection = "jdbc:mysql://127.0.01:3306/" + db_name;
        try
        {
            con = DriverManager.getConnection(connection, "root", "");
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
    }

    /** Metodo per eseguire un SELECT
     *
     * @param what colonna della tabella da vedere ('*' per vedere tutta la tabella)
     * @param from nome della tabella
     * @param where condizione: nome del campo da valutare
     * @param is condizione: valore da comparare
     * @return stringa indicante la risposta della query
     * @throws SQLException se si verifica un'eccezione database
     */
    public String SELECT(String what, String from, String where, String is) throws SQLException
    {
        StringBuilder result = new StringBuilder();
        try
        {
            if (!con.isValid(5))
                return null;
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }

        String query = "SELECT " + what + " FROM " + from + " WHERE " + where + " = ?";

        try
        {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, is);
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                {
                    result.append(rs.getString(i)).append("\t");
                    if (rs.getString(i).length() < 8) result.append("\t");
                }
                result.append("\n");
            }
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
        return result.toString();
    }

    /** Metodo per ottenere tutti i record di una tabella
     *
     * @param from nome della tabella
     * @return stringa con tutti i record
     * @throws SQLException se si verifica un'eccezione nel database
     */
    public String SELECT_ALL(String from) throws SQLException
    {
        StringBuilder result = new StringBuilder();
        try
        {
            if (!con.isValid(5))
                return null;
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
        String query = "SELECT * FROM " + from;

        try
        {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                {
                    result.append(rs.getString(i)).append("\t");
                    if (rs.getString(i).length() < 8) result.append("\t");
                }
                result.append("\n");
            }
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
        return result.toString();
    }

    /** Metodo per inserire record dentro una tabella generica
     * Siccome il metodo deve essere adattato alla tabella, viene mantenuto un esempio ipotetico con una tabella "Persone"
     * @param nome nome persona da inserire (esempio)
     * @param cognome cognome persona da inserire (esempio)
     * @param eta età persona da inserire (esempio)
     * @param sesso sesso della persona da inserire (esempio)
     * @return boolean indicante se l'inserimento è avvenuto con successo
     * @throws SQLException se si verifica un'eccezione nel database
     */
    public boolean INSERT_INTO(String nome, String cognome, int eta, String sesso) throws SQLException
    {
        try
        {
            if (!con.isValid(5))
                return false;
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
        String query = "INSERT INTO persone (nome, cognome, eta, sesso) VALUES (?, ?, ?, ?)";
        try
        {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, nome);
            statement.setString(2, cognome);
            statement.setInt(3, eta);
            statement.setString(4, sesso);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
        return true;
    }

    /** Metodo per aggiornare un record dentro una tabella generica
     * Siccome il metodo deve essere adattato alla tabella, viene mantenuto un esempio ipotetico con una tabella "Persone"
     * @param nome nome della persona da eliminare (esempio)
     * @return boolean indicante se l'eliminazione è avvenuta con successo
     * @throws SQLException se si verifica un'eccezione nel database
     */
    public boolean DELETE(String nome) throws SQLException
    {
        try
        {
            if (!con.isValid(5))
                return false;
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
        String query = "DELETE FROM persone WHERE Nome = ?";
        try
        {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, nome);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
        return true;
    }

    /** Metodo per aggiornare un record dentro una tabella generica
     * Siccome il metodo deve essere adattato alla tabella, viene mantenuto un esempio ipotetico con una tabella "Persone"
     * @param id id del record da aggiornare (esempio)
     * @param nome nome della persona da aggiornare (esempio)
     * @param cognome cognome della persona da aggiornare (esempio)
     * @param eta età della persona da aggiornare (esempio)
     * @param sesso sesso della persona da aggiornare (esempio)
     * @return boolean indicante se la modifica è avvenuta con successo
     * @throws SQLException se si verfica un'eccezione nel database
     */
    public boolean UPDATE(int id, String nome, String cognome, Integer eta, String sesso) throws SQLException
    {
        try
        {
            if (!con.isValid(5))
                return false;
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
        String query = "UPDATE persone SET nome = ?, Cognome = ?, eta = ?, Sesso = ? WHERE ID = ?";
        try
        {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, nome);
            statement.setString(2, cognome);
            statement.setInt(3, eta);
            statement.setString(4, sesso);
            statement.setInt(5, id);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
        return true;
    }
}
