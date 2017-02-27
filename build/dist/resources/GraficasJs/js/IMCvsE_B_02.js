var datapointsUser = [NaN, NaN, NaN, NaN, NaN, NaN, NaN, 17.5];
var datapointsCero = [13.4, 16.3, 17.2, 17.3, 17.3, 17.0, 16.8, 16.6, 16.3, 16.1, 16.0, 15.8, 15.7];
var datapointsUnoNegativo = [12.2, 15.0, 15.8, 16.0, 15.9, 15.7, 15.5, 15.3, 15.1, 14.9, 14.8, 14.7, 14.6];
var datapointsUnoPositivo = [14.8, 17.8, 18.7, 18.8, 18.7, 18.5, 18.2, 18.0, 17.7, 17.5, 17.3, 17.2, 17.0];
var datapointsDosNegativo = [11.1, 13.7, 14.5, 14.7, 14.7, 14.6, 14.4, 14.2, 14.0, 13.9, 13.7, 13.6, 13.6];
var datapointsDosPositivo = [16.3, 19.4, 20.3, 20.5, 20.4, 20.1, 19.8, 19.5, 19.3, 19.0, 18.8, 18.7, 18.5];
var datapointsTresPositivo = [18.1, 21.1, 22.1, 22.3, 22.2, 22.0, 21.6, 21.3, 21.0, 20.8, 20.6, 20.4, 20.3];

var config6 = {
    type: 'line',
    data: {
        labels: ["0", "2", "4", "6", "8", "10", "1 Año", "2", "4", "6", "8", "10", "2 Años"],
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

