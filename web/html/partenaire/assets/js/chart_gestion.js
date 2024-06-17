document.addEventListener('DOMContentLoaded', (event) => {
    var ctx = document.getElementById('chart_canvas').getContext('2d');
    // Gérer les données ici
    const label =  ['Mon','Tue','Wed','Thu','Fri','Sat','Sun']
    const datas = [11,12,6,9,18,3,9]
    const secondData = [15,10,8,7,12,10,20]

    const dataMax = datas.indexOf(Math.max(...datas))

    const data = {
        labels:label,
        datasets:[{
            label:'This month',
            data:datas,
            backgroundColor:(context)=>{
                const bgColor = [
                    'rgba(65, 105, 225, 0.3)',
                    'rgba(65, 105, 225, 0.2)',
                    'rgba(65, 105, 225, 0.1)',
                    'rgba(65, 105, 225, 0)',
                ]
                if(!context.chart.chartArea){
                    return
                }

                const {ctx,data,chartArea:{top,bottom}} = context.chart
                const gradientBg = ctx.createLinearGradient(0,top,0,bottom)
                const colorTranches = 1 / (bgColor.length-1)
                for(let i = 0; i<bgColor.length; i++){
                    gradientBg.addColorStop(0 + i * colorTranches,bgColor[i])
                }
                return gradientBg
            },
            borderColor:['rgba(65, 105, 225, 1)'],
            borderWidth:3,
            pointRadius:(context)=>{
                var index = context.dataIndex
                return index === dataMax ? 7 : 0
            },
            pointBackgroundColor:(context)=>{
                var index = context.dataIndex
                return index === dataMax ? 'rgba(65, 105, 225, 1)' : null
            },
            datalabels: {
                display: function(context) {
                    return context.dataIndex === dataMax // Afficher pour le point Max
                }
            },
            tension:.3,
            fill:true
        },{
            label:'Last Month',
            data:secondData,
            borderColor:['rgba(255, 79, 121, 1)'],
            datalabels:{
                display:false
            },
            borderWidth:3,
            borderDash:[5,5],
            tension:.4,
            pointRadius:0
        }]
    };

    const config = {
        type:'line',
        data,
        options: {
            scales: {
                y:{
                    font:{
                            size:20
                        },
                    beginAtZero: true,
                    ticks: {
                        stepSize: 5, // Ecart de 10
                        padding:10
                    },
                    title:{
                        display:true,
                        text:'Sale',
                        align:'center',
                        font:{
                            size:20,
                            weight:'bold'
                        }
                    }
                },
                x:{
                    display:false
                }
            },
            layout:{
                padding:30
            },
            plugins:{
                legend:{
                    display:true,
                    position:'bottom',
                    labels:{
                        color:'rgba(65, 105, 225, 1)',
                        font:{
                            size:15
                        },
                        boxWidth:20,
                        padding:15,
                        usePointStyle:true
                    }
                },
                tooltip:{
                    enabled:true
                },
                datalabels: {
                    color: 'white', // Couleur de la police des labels
                    backgroundColor: 'black', // Couleur de fond des labels
                    borderRadius: 4, // Arrondir les coins des labels
                    padding:{
                        top:20,
                        bottom:20,
                        left:50,
                        right:50
                    },
                    align: 'end',
                    anchor: 'end',
                    font: {
                        size: 18, // Taille de la police des labels
                        weight:'bold'
                    },
                    formatter: function(value) {
                       return '$'+value
                    }
                }
        }
    },
    plugins: [ChartDataLabels]
}
    var myChart = new Chart(ctx,config);
});

// Gestion de l'incrementation

const incr = document.querySelectorAll('.inc_btn')
const descr = document.querySelectorAll('.desc_btn')

incr.forEach(element=>{
    element.addEventListener('click',()=>{
        const parents = element.parentNode
        let input = parents.querySelector('input[type="number"]')

        const max = input.dataset.max
        let value = Number(input.value)
        input.value = value + 1 > max ? value : value +1
    })
})

descr.forEach(element=>{
    element.addEventListener('click',()=>{
        const parents = element.parentNode
        let input = parents.querySelector('input[type="number"]')

        const min = input.dataset.min
        let value = Number(input.value)
        input.value = value - 1 < min ? value : value -1
    })
})
