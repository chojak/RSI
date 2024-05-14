using ServiceReference1;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace RSI_PROJEKT_WS_CLIENT
{
    /// <summary>
    /// Interaction logic for AddEventWindow.xaml
    /// </summary>
    public partial class AddEventWindow : Window
    {
        public WebServiceClient service;
        public AddEventWindow(WebServiceClient service)
        {
            InitializeComponent();
            this.service = service;
        }

        private void Confirm_Button_Click(object sender, RoutedEventArgs e)
        {
            var name = Event_Name_Textbox.Text;
            var type = Event_Type_Textbox.Text;
            var date = (Event_Date_Datepicker as DatePicker).SelectedDate;
            var description = Event_Description_Textbox.Text;

            if (date == null)
            {
                Event_Date_Datepicker.Background = Brushes.Red;
                return;
            }
            service.addEvent(name, type, (DateTime)date, description);
            this.Close();
        }

        private void Cancel_Button_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }
}
