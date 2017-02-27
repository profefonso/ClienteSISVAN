var datapointsUser = [NaN, NaN, NaN, NaN, NaN, NaN, 100];
var datapointsCero = [87.1, 88.8, 90.4, 91.9, 93.4, 94.8, 96.1, 97.4, 98.6, 99.9, 101.0, 102.2, 103.3, 104.4, 105.6, 106.7, 107.8, 108.9, 110.0];
var datapointsUnoNegativo = [84.1, 85.6, 87.1, 88.5, 89.9, 91.1, 92.4, 93.6, 94.7, 95.9, 97.0, 98.1, 99.1, 100.2, 101.2, 102.3, 103.3, 104.3, 105.3];
var datapointsUnoPositivo = [90.2, 92.0, 93.7, 95.3, 96.9, 98.4, 99.8, 101.2, 102.5, 103.8, 105.1, 106.3, 107.5, 108.7, 109.9, 111.1, 112.3, 113.4, 114.6];
var datapointsDosNegativo = [81.0, 82.5, 83.8, 85.1, 86.4, 87.5, 88.7, 89.8, 90.9, 91.9, 93.0, 94.0, 94.9, 95.9, 96.9, 97.8, 98.8, 99.7, 100.7];
var datapointsDosPositivo = [93.2, 95.2, 97.0, 98.7, 100.4, 102.0, 103.5, 105.0, 106.4, 107.8, 109.1, 110.4, 111.7, 113.0, 114.2, 115.5, 116.7, 118.0, 119.2];

var config4 = {
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
            text:'Talla Para la Edad Ninos'
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
                    labelString: 'Longitud (cm)'
                },
                ticks: {
                    fixedStepSize: 5,
                    stepSize: 5
                }
            }]
        }
    }
};

