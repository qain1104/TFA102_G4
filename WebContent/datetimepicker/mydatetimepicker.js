
$('input[name="registTimeAndEnd"]').daterangepicker({
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
    "startDate": "2021/09/05",
    "endDate": "2021/09/12"
}, function(start, end) {
//  console.log("從"+start.format('YYYY-MM-DD hh:mm') + '到' + end.format('YYYY-MM-DD hh:mm'));
  console.log(start.format('YYYY-MM-DD HH:mm:ss'))
  console.log(end.format('YYYY-MM-DD HH:mm:ss'))
})

$('input[name="exerciseTime"]').daterangepicker({
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
    "startDate": "2021/9/5",
    "endDate": "2021/1/20",
    "applyButtonClasses": "btn-sucess",
    "cancelClass": "btn-sucess"
}, function(start, end, label) {
  console.log('報名時間' + start.format('YYYY-MM-DD HH:mm'));
});