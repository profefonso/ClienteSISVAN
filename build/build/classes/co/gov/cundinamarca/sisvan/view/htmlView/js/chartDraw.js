function draw(param) {
    var config = {
        type: 'line',
        data: {
            labels: param.conf.Xlabels,
            datasets: []
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: param.conf.Tittle
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
                        labelString: param.conf.Xtittle
                    },
                    ticks: {
                        fontSize: 10
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: param.conf.Ytittle
                    },
                    ticks: {
                        fixedStepSize: param.conf.scale,
                        stepSize: param.conf.scale
                    }
                }]
            }
        }
    };
    var i;
    for (i = 0; i < param.data.LINEAS.length; i++) {
        if (i === 0) {
            var lgb = '1';
        }
        if (param.data.LINEAS[i].area) {
            var lgb = '1';
        }
        config.data.datasets.push({'label': param.data.LINEAS[i].label,
            'data': param.data.LINEAS[i].dataR,
            'borderColor': 'rgba(' + param.data.LINEAS[i].colorR + ', ' + param.data.LINEAS[i].colorG + ', ' + param.data.LINEAS[i].colorB + ', 0.7)',
            'backgroundColor': 'rgba(' + param.data.LINEAS[i].colorBR + ', ' + param.data.LINEAS[i].colorBG + ', ' + param.data.LINEAS[i].colorBB + ', ' + lgb + ')',
            'fill': param.data.LINEAS[i].area,
            'lineTension': 0,
            'borderWidth':2,
            'pointRadius': param.data.LINEAS[i].colorR === '0' && param.data.LINEAS[i].colorG === '0' && param.data.LINEAS[i].colorB === '255' ? 3 : -1
        });
    }

    var ctx = document.getElementById("canvas_Draw").getContext("2d");
    window.myLine = new Chart(ctx, config);
}