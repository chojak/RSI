using ServiceReference2;
using System.Net;
using System.ServiceModel;
using System.ServiceModel.Security;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace RSI_PROJEKT_WS_CLIENT
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public List<@event> events;
        public WebServiceClient service;
        public List<@event> displayedEvents;
        public MainWindow()
        {
            InitializeComponent();

            service = new WebServiceClient();
            service.ClientCredentials.ServiceCertificate.SslCertificateAuthentication =
                new X509ServiceCertificateAuthentication()
                {
                    CertificateValidationMode = X509CertificateValidationMode.None,
                    RevocationMode = System.Security.Cryptography.X509Certificates.X509RevocationMode.NoCheck
                };

            events = service.getAllEvents().ToList();
            EventComboBox.ItemsSource = events;
            EventComboBox.DisplayMemberPath = "name";

            EventListBox.DisplayMemberPath = "name";
        }

        private void ComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (e.AddedItems.Count == 0)
                return; 

            var selectedItem = (e.AddedItems[0] as @event);
            var newEventList = new List<@event>();
            var newEvent = service.getEventInformation(selectedItem.id);
            newEventList.Add(newEvent);
            fillEventListBox(newEventList);
        }

        private void EventsForDayChanged(object sender, EventArgs e)
        {
            DateTime? selectedDate = (sender as DatePicker).SelectedDate;
            var newEventList = new List<@event>();
          
            if (selectedDate != null)
            {
                var newEventArray = service.getEventsForDay((DateTime)selectedDate);
                if (newEventArray != null && newEventArray.Length > 0) 
                {
                    newEventList = newEventArray.ToList();
                }

            }
            fillEventListBox(newEventList);
        }

        private void EventsForWeekChanged(object sender, EventArgs e)
        {
            DateTime? selectedDate = (sender as DatePicker).SelectedDate;
            var newEventList = new List<@event>();

            if (selectedDate != null)
            {
                var newEventArray = service.getEventsForWeek((DateTime)selectedDate);
                if (newEventArray != null && newEventArray.Length > 0)
                {
                    newEventList = newEventArray.ToList();
                }

            }
            fillEventListBox(newEventList);
        }

        private void fillEventListBox(List<@event> events)
        {
            EventListBox.ItemsSource = events;
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            var addEventWindow = new AddEventWindow(service);
            addEventWindow.ShowDialog();

            events = service.getAllEvents().ToList();
            EventComboBox.ItemsSource = events;
        }

        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            var editEventWindow = new EditEventWindow(events, service);
            editEventWindow.ShowDialog();

            events = service.getAllEvents().ToList();
            EventComboBox.ItemsSource = events;
        }

        private void Button_Click_3(object sender, RoutedEventArgs e)
        {
            List<@event> eventsPicked = new List<@event>();
            eventsPicked.AddRange(EventListBox.Items.OfType<@event>());
            var tmp = eventsPicked.ToArray();

            var pdf = service.generatePDFReport(tmp);

            System.IO.File.WriteAllBytes("hello.pdf", pdf);
        }
    }
}