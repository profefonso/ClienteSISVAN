var datapointsUser = [NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN,  16.3];
var datapointsCero = [7.4, 8.0, 8.6, 9.1, 9.6, 10.1, 10.6, 11.1, 11.7, 12.3, 12.9, 13.5, 14.1, 14.7, 15.4, 16.1, 16.8, 17.7, 18.5, 19.4, 20.4, 21.4, 22.4];
var datapointsUnoNegativo = [6.9, 7.4, 7.9, 8.4, 8.9, 9.3, 9.7, 10.2, 10.8, 11.3, 11.9, 12.4, 13.0, 13.6, 14.2, 14.8, 15.5, 16.2, 17.0, 17.8, 18.6, 19.5, 20.4];
var datapointsUnoPositivo = [8.1, 8.7, 9.3, 9.9, 10.5, 11.0, 11.5, 12.1, 12.7, 13.3, 14.0, 14.6, 15.3, 15.9, 16.7, 17.5, 18.4, 19.3, 20.2, 21.3, 22.4, 23.5, 24.6];
var datapointsDosNegativo = [6.3, 6.8, 7.3, 7.8, 8.2, 8.6, 9.0, 9.4, 10.0, 10.5, 11.0, 11.5, 12.0, 12.5, 13.1, 13.7, 14.3, 14.9, 15.6, 16.3, 17.1, 17.9, 18.6];
var datapointsDosPositivo = [8.8, 9.5, 10.2, 10.8, 11.4, 12.0, 12.6, 13.1, 13.8, 14.5, 15.2, 15.9, 16.6, 17.4, 18.2, 19.1, 20.1, 21.1, 22.2, 23.4, 24.6, 25.9, 27.2];
var datapointsTresNegativo = [5.9, 6.3, 6.8, 7.2, 7.6, 8.0, 8.3, 8.7, 9.2, 9.7, 10.2, 10.7, 11.1, 11.6, 12.1, 12.6, 13.2, 13.8, 14.4, 15.0, 15.7, 16.4, 17.1];
var datapointsTresPositivo = [9.6, 10.4, 11.1, 11.8, 12.5, 13.1, 13.7, 14.4, 15.1, 15.8, 16.6, 17.3, 18.1, 18.9, 19.9, 20.9, 22.0, 23.2, 24.4, 25.8, 27.2, 28.6, 30.1];

var config2 = {
    type: 'line',
    data: {
        labels: ["65", "67.5", "70", "72.5", "75", "77.5", "80", "82.5", "85", "87.5", "90", "92.5", "95", "97.5", "100", "102.5", "105", "107.5", "110", "112.5", "115", "117.5", "120"],
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

