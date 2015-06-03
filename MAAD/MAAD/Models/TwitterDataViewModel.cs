using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MAAD.Models
{
    public class TwitterDataViewModel
    {
        public string Linguaggio { get; set; }
        public DateTime Data { get; set; }
        public int Num_tweet { get; set; }
        public string Nazione { get; set; }
    }
}