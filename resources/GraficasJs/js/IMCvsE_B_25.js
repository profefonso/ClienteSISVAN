var datapointsUser = [NaN, NaN, NaN, NaN, NaN, NaN, NaN, 17.5];
var datapointsCero = [16.0, 15.9, 15.9, 15.8, 15.7, 15.7, 15.6, 15.5, 15.5, 15.4, 15.4, 15.4, 15.3, 15.3, 15.3, 15.3, 15.2, 15.2, 15.2];
var datapointsUnoNegativo = [14.8, 14.8, 14.7, 14.6, 14.6, 14.5, 14.4, 14.4, 14.3, 14.3, 14.2, 14.2, 14.1, 14.1, 14.1, 14.0, 14.0, 14.0, 14.0];
var datapointsUnoPositivo = [17.3, 17.3, 17.2, 17.1, 17.0, 17.0, 16.9, 16.8, 16.8, 16.8, 16.7, 16.7, 16.7, 16.7, 16.6, 16.6, 16.6, 16.6, 16.6];
var datapointsDosNegativo = [13.8, 13.7, 13.6, 13.6, 13.5, 13.4, 13.4, 13.3, 13.2, 13.2, 13.1, 13.1, 13.1, 13.0, 13.0, 13.0, 12.9, 12.9, 12.9];
var datapointsDosPositivo = [18.9, 18.8, 18.7, 18.6, 18.5, 18.4, 18.4, 18.3, 18.2, 18.2, 18.2, 18.2, 18.2, 18.2, 18.2, 18.2, 18.2, 18.3, 18.3];
var datapointsTresPositivo = [20.6, 20.5, 20.4, 20.2, 20.1, 20.0, 20.0, 19.9, 19.9, 19.8, 19.8, 19.8, 19.9, 19.9, 19.9, 20.0, 20.1, 20.2, 20.3];

var config7 = {
    type: 'line',
    data: {
        labels: ["2 Años", "2", "4", "6", "8", "10", "3 Años", "2", "4", "6", "8", "10", "4 Años", "2", "4", "6", "8", "10", "5 Años"],
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
            text:'IMC (Kg/m2)'
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
                    labelString: 'IMC para la Edad Niños'
                },
                ticks: {
                    fixedStepSize: 1,
                    stepSize: 1
                }
            }]
        }
    }
};

