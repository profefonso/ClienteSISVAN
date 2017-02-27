var datapointsUser = [NaN, NaN, NaN, NaN, NaN, NaN, 10.6];
var datapointsCero = [3.3, 5.6, 7.0, 7.9, 8.6, 9.2, 9.6, 10.1, 10.5, 10.9, 11.3, 11.8, 12.2];
var datapointsUnoNegativo = [2.9, 4.9, 6.2, 7.1, 7.7, 8.2, 8.6, 9.0, 9.4, 9.8, 10.1, 10.5, 10.8];
var datapointsUnoPositivo = [3.9, 6.3, 7.8, 8.8, 9.6, 10.2, 10.8, 11.3, 11.7, 12.2, 12.7, 13.2, 13.6];
var datapointsDosNegativo = [2.5, 4.3, 5.6, 6.4, 6.9, 7.4, 7.7, 8.1, 8.4, 8.8, 9.1, 9.4, 9.7];
var datapointsDosPositivo = [4.4, 7.1, 8.7, 9.8, 10.7, 11.4, 12.0, 12.6, 13.1, 13.7, 14.2, 14.7, 15.3];

var config8 = {
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

