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
    /// Interaction logic for EditEventWindow.xaml
    /// </summary>
    public partial class EditEventWindow : Window
    {
        public List<@event> events;
        public ImageServiceClient service;
        public EditEventWindow(List<@event> events, ImageServiceClient service)
        {
            InitializeComponent();
            this.events = events;
            this.service = service;
            EventComboBox.ItemsSource = events;
            EventComboBox.DisplayMemberPath = "name";
        }

        private void ComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            var selectedEvent = EventComboBox.SelectedItem as @event;
            Event_Name_Textbox.Text = selectedEvent.name;
            Event_Type_Textbox.Text = selectedEvent.type;
            Event_Description_Textbox.Text = selectedEvent.description;
            Event_Date_Datepicker.SelectedDate = selectedEvent.date;
        }

        private void Confirm_Button_Click(object sender, RoutedEventArgs e)
        {
            var selectedEvent = EventComboBox.SelectedItem as @event;
            var name = Event_Name_Textbox.Text;
            var type = Event_Type_Textbox.Text;
            var date = (Event_Date_Datepicker as DatePicker).SelectedDate;
            var description = Event_Description_Textbox.Text;

            if (date == null)
            {
                Event_Date_Datepicker.Background = Brushes.Red;
                return;
            }
            service.modifyEventInformation(selectedEvent.id ,name, type, (DateTime)date, description);
            this.Close();
        }

        private void Cancel_Button_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }
}
