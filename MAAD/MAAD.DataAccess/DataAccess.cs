using MAAD.DataAccess.Object_Model;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;
using Npgsql;

namespace MAAD.DataAccess
{
    public class DataAccess
    {
        public static IEnumerable<Twitter> GetData()
        {
            // Recupero stringa di connessione
            // string cs = ConfigurationManager.ConnectionStrings["cs"].ConnectionString;
            string cs = "Server=52.17.93.234; Port=5432; Database=db_twitter; User ID=graphs; Password=graphs; Pooling=true; Min Pool Size=0; Max Pool Size=100; Connection Lifetime=0;";

            // Creo la connessione verso il database
            using (NpgsqlConnection connection = new NpgsqlConnection(cs))
            {
                // Apro connessione
                connection.Open();

                string query = "SELECT * FROM vw_graphs";

                // Creo il comando
                using (NpgsqlCommand command = connection.CreateCommand())
                {
                    // Dichiaro il tipo di comando
                    command.CommandText = query;
                    command.CommandType = System.Data.CommandType.Text;

                    
                    NpgsqlDataReader reader = command.ExecuteReader();

                    List<Twitter> lista = new List<Twitter>();

                    while (reader.Read())
                    {
                        Twitter t = new Twitter ();
                        t.Linguaggio = (string)reader["Language"];
                        t.Data = (DateTime)reader["Date"];
                        t.Nazione = (string)reader["Nation"];
                        t.Num_tweet = (int)reader["Counter"];

                        //s.HomePage = reader["HomePage"] as string;

                        lista.Add(t);
                    }
                    return lista;
                }
            }
        }
    }
}
