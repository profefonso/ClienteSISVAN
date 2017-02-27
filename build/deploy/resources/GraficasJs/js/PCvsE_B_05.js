var datapointsUser = [NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, NaN, 47.5];
var datapointsCero = [34.5, 39.1, 41.6, 43.3, 44.5, 45.4, 46.1, 46.6, 47.0, 47.4, 47.7, 48.0, 48.3, 48.5, 48.7, 48.9, 49.1, 49.3, 49.5, 49.6, 49.7, 49.9, 50.0, 50.1, 50.2, 50.3, 50.4, 50.5, 50.6, 50.7, 50.7];
var datapointsUnoNegativo = [33.2, 38.0, 40.4, 42.1, 43.3, 44.1, 44.8, 45.3, 45.7, 46.0, 46.4, 46.6, 46.9, 47.1, 47.3, 47.5, 47.7, 47.9, 48.0, 48.2, 48.3, 48.4, 48.5, 48.7, 48.7, 48.8, 48.9, 49.0, 49.1, 49.2, 49.2];
var datapointsUnoPositivo = [35.7, 40.3, 42.8, 44.6, 45.8, 46.7, 47.4, 47.9, 48.3, 48.7, 49.0, 49.3, 49.6, 49.9, 50.1, 50.3, 50.5, 50.7, 50.9, 51.0, 51.2, 51.3, 51.4, 51.6, 51.7, 51.8, 51.9, 52.0, 52.1, 52.1, 52.2];
var datapointsDosNegativo = [31.9, 36.8, 39.2, 40.9, 42.0, 42.9, 43.5, 44.0, 44.4, 44.7, 45.0, 45.3, 45.5, 45.8, 46.0, 46.1, 46.3, 46.5, 46.6, 46.8, 46.9, 47.0, 47.1, 47.2, 47.3, 47.4, 47.5, 47.5, 47.6, 47.7, 47.7];
var datapointsDosPositivo = [37.0, 41.5, 44.0, 45.8, 47.0, 47.9, 48.6, 49.2, 49.6, 50.0, 50.4, 50.7, 51.0, 51.2, 51.5, 51.7, 51.9, 52.1, 52.3, 52.5, 52.6, 52.8, 52.9, 53.0, 53.1, 53.2, 53.4, 53.5, 53.5, 53.6, 53.7];

var config5 = {
    type: 'line',
    data: {
        labels: ["0", "2", "4", "6", "8", "10", "1 Año", "2", "4", "6", "8", "10", "2 Años", "2", "4", "6", "8", "10", "3 Años", "2", "4", "6", "8", "10", "4 Años", "2", "4", "6", "8", "10", "5 Años"],
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
            text:'Perimetro Cefalico'
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
                    labelString: 'Perímetro Cefálico (cm)'
                },
                ticks: {
                    fixedStepSize: 2,
                    stepSize: 2
                }
            }]
        }
    }
};

