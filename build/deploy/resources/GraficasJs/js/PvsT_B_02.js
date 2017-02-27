var datapointsUser = [NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, 12.5];
var datapointsCero = [2.4, 2.9, 3.3, 3.9, 4.5, 5.3, 6, 6.7, 7.3, 7.9, 8.4, 9, 9.5, 10, 10.4, 10.9, 11.5, 12.1, 12.7, 13.3, 13.9, 14.5, 15.2, 15.9, 16.6, 17.4, 18.3];
var datapointsUnoNegativo = [2.2, 2.6, 3, 3.6, 4.2, 4.9, 5.5, 6.1, 6.7, 7.2, 7.8, 8.3, 8.8, 9.2, 9.6, 10.1, 10.6, 11.2, 11.8, 12.3, 12.8, 13.4, 14, 14.6, 15.3, 16, 16.8];
var datapointsUnoPositivo = [2.7, 3.1, 3.6, 4.2, 5, 5.7, 6.5, 7.2, 7.9, 8.5, 9.2, 9.8, 10.3, 10.9, 11.4, 11.9, 12.5, 13.2, 13.8, 14.4, 15.1, 15.7, 16.5, 17.3, 18.1, 19, 20];
var datapointsDosNegativo = [2, 2.4, 2.8, 3.3, 3.8, 4.5, 5.1, 5.7, 6.2, 6.7, 7.2, 7.6, 8.1, 8.5, 8.9, 9.3, 9.8, 10.4, 10.9, 11.4, 11.9, 12.4, 12.9, 13.5, 14.1, 14.7, 15.4];
var datapointsDosPositivo = [3, 3.4, 4, 4.6, 5.4, 6.3, 7.1, 7.9, 8.6, 9.3, 10, 10.6, 11.3, 11.9, 12.4, 13, 13.6, 14.3, 15, 15.7, 16.4, 17.1, 18, 18.8, 19.8, 20.8, 21.9];
var datapointsTresNegativo = [1.9, 2.2, 2.6, 3, 3.6, 4.1, 4.7, 5.2, 5.7, 6.2, 6.6, 7.1, 7.5, 7.9, 8.2, 8.6, 9.1, 9.6, 10.1, 10.6, 11, 11.5, 12, 12.5, 13, 13.6, 14.2];
var datapointsTresPositivo = [3.3, 3.8, 4.4, 5.1, 6, 6.9, 7.8, 8.6, 9.4, 10.2, 10.9, 11.6, 12.3, 13, 13.6, 14.2, 14.9, 15.6, 16.4, 17.1, 17.9, 18.7, 19.6, 20.6, 21.7, 22.8, 24.1];

var config = {
    type: 'line',
    data: {
        labels: ["45", "47.5", "50", "52.5", "55", "57.5", "60", "62.5", "65", "67.5", "70", "72.5", "75", "77.5", "80", "82.5", "85", "87.5", "90", "92.5", "95", "97.5", "100", "102.5", "105", "107.5", "110"],
        datasets: [{
            label: "Usuario",
            data: datapointsUser,
            borderColor: 'rgba(0, 0, 0, 0.7)',
            backgroundColor: 'rgba(0, 0, 0, 1)',
            fill: false,
            lineTension: 0
        },{
            label: "0",
            data: datapointsCero,
            borderColor: 'rgba(100, 143, 44, 0.7)',
            backgroundColor: 'rgba(100, 143, 44, 0.7)',
            fill: false,
            cubicInterpolationMode: 'monotone'
        }, {
            label: "-1",
            data: datapointsUnoNegativo,
            borderColor: 'rgba(245, 245, 15, 0.7)',
            backgroundColor: 'rgba(0, 0, 0, 0)',
            fill: false,
        
        },{
            label: "+1",
            data: datapointsUnoPositivo,
            borderColor: 'rgba(245, 245, 15, 0.7)',
            backgroundColor: 'rgba(0, 0, 0, 0)',
            fill: false,
        },{
            label: "-2",
            data: datapointsDosNegativo,
            borderColor: 'rgba(245, 92, 15, 0.7)',
            backgroundColor: 'rgba(0, 0, 0, 0)',
            fill: false,
            lineTension: 0
        },{
            label: "+2",
            data: datapointsDosPositivo,
            borderColor: 'rgba(245, 92, 15, 0.7)',
            backgroundColor: 'rgba(0, 0, 0, 0)',
            fill: false,
            lineTension: 0
        },{
            label: "-3",
            data: datapointsTresNegativo,
            borderColor: 'rgba(255, 0, 0, 0.7)',
            backgroundColor: 'rgba(0, 0, 0, 0)',
            fill: false,
            lineTension: 0
        },{
            label: "+3",
            data: datapointsTresPositivo,
            borderColor: 'rgba(255, 0, 0, 0.7)',
            backgroundColor: 'rgba(0, 0, 0, 0)',
            fill: false,
            lineTension: 0
        }]
    },
    options: {
    	responsive: true,
        title:{
            display:false,
            text:'Peso Para la talla Ninos'
        },
        tooltips: {
            mode: 'label'
        },
        hover: {
            mode: 'dataset'
        },
        scales: {
            xAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Longitud (cm)'
                },
                ticks: {
                  	fontSize: 10	
                }
            }],
            yAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Peso (Kg)'
                },
                ticks: {
                    fixedStepSize: 1,
                    stepSize: 1
                }
            }]
        }
    }
};