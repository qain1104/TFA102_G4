$('input[name="dates1"]').daterangepicker({
    "showDropdowns": true,
    "minYear": 2020,
    "maxYear": 2025,
    "timePicker": true,
    "timePicker24Hour": true,
    "autoApply": true,
    "drops": "up",
    "locale": {
        "format": "YYYY-MM-DD HH:mm",
        "separator": "至",
        "applyLabel": "Apply",
        "cancelLabel": "Cancel",
        "fromLabel": "From",
        "drops": "up",
        "toLabel": "To",
        "customRangeLabel": "Custom",
        "weekLabel": "W",
        "daysOfWeek": [
            "Su",
            "Mo",
            "Tu",
            "We",
            "Th",
            "Fr",
            "Sa"
        ],
        "monthNames": [
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        ],
        "firstDay": 1
    },
    "startDate": "2021/08/14",
    "endDate": "2021/08/20"
}, function(start, end) {
  console.log("從"+start.format('YYYY-MM-DD hh:mm') + '到' + end.format('YYYY-MM-DD hh:mm'));
})

$('input[name="dates2"]').daterangepicker({
    "singleDatePicker": true,
    "minYear": 2021,
    "timePicker": true,
    "timePicker24Hour": true,
    "locale": {
        "format": "YYYY-MM-DD HH:mm",
        "separator": " - ",
        "applyLabel": "Apply",
        "cancelLabel": "Cancel",
        "fromLabel": "From",
        "toLabel": "To",
        "customRangeLabel": "Custom",
        "drops": "up",
        "weekLabel": "W",
        "daysOfWeek": [
            "Su",
            "Mo",
            "Tu",
            "We",
            "Th",
            "Fr",
            "Sa"
        ],
        "monthNames": [
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        ],
        "firstDay": 1
    },
    "startDate": "2021/1/1",
    "endDate": "2021/1/20",
    "applyButtonClasses": "btn-sucess",
    "cancelClass": "btn-sucess"
}, function(start, end, label) {
  console.log('報名時間' + start.format('YYYY-MM-DD HH:mm'));
});