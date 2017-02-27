var datapointsUser = [NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, 14.6];
var datapointsCero = [12.2, 12.5, 12.9, 13.3, 13.7, 14.0, 14.3, 14.7, 15.0, 15.3, 15.7, 16.0, 16.3, 16.7, 17.0, 17.3, 17.7, 18.0, 18.3];
var datapointsUnoNegativo = [10.8, 11.2, 11.5, 11.8, 12.1, 12.4, 12.7, 13.0, 13.3, 13.6, 13.8, 14.1, 14.4, 14.7, 15.0, 15.2, 15.5, 15.8, 16.0];
var datapointsUnoPositivo = [13.6, 14.1, 14.5, 15.0, 15.4, 15.8, 16.2, 16.6, 17.0, 17.4, 17.8, 18.2, 18.6, 19.0, 19.4, 19.8, 20.2, 20.6, 21.0];
var datapointsDosNegativo = [9.7, 10.0, 10.2, 10.5, 10.8, 11.0, 11.3, 11.5, 11.8, 12.0, 12.2, 12.5, 12.7, 12.9, 13.2, 13.4, 13.6, 13.8, 14.1];
var datapointsDosPositivo = [15.3, 15.8, 16.3, 16.9, 17.4, 17.8, 18.3, 18.8, 19.3, 19.7, 20.2, 20.7, 21.2, 21.7, 22.2, 22.7, 23.2, 23.7, 24.2];

var config9 = {
    type: 'line',
    data: {
        labels: ["2 Años", "2", "4", "6", "8", "10", "3  Años", "2", "4", "6", "8", "10", "4  Años", "2", "4", "6", "8", "10", "5 Años"],
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
        }]
    },
    options: {
    	responsive: true,
        title:{
            display:false,
            text:'Peso Para la Edad'
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
                    labelString: 'Edas (En meses y Años Cumplidos)'
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

