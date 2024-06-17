$(function(){'use strict'
  var ticksStyle={fontColor:'#495057',fontStyle:'bold'}
  var mode='index'
  var intersect=true
  var $salesChart=$('#sales-chart')
  var salesChart=new Chart($salesChart,{type:'bar',data:{labels:['JUN','JUL','AGO','SET','OUT','NOV','DEZ'],datasets:[{backgroundColor:'#007bff',borderColor:'#007bff',data:[1000,2000,3000,2500,2700,2500,3000]},{backgroundColor:'#ced4da',borderColor:'#ced4da',data:[700,1700,2700,2000,1800,1500,2000]}]},options:{maintainAspectRatio:false,tooltips:{mode:mode,intersect:intersect},hover:{mode:mode,intersect:intersect},legend:{display:false},scales:{yAxes:[{gridLines:{display:true,lineWidth:'4px',color:'rgba(0, 0, 0, .2)',zeroLineColor:'transparent'},ticks:$.extend({beginAtZero:true,callback:function(value){if(value>=1000){value/=1000
  value+='k'}
  return '$'+value}},ticksStyle)}],xAxes:[{display:true,gridLines:{display:false},ticks:ticksStyle}]}}})
  var $visitorsChart=$('#visitors-chart')
  var visitorsChart=new Chart($visitorsChart,{data:{labels:['18th','20th','22nd','24th','26th','28th','30th'],datasets:[{type:'line',data:[100,120,170,167,180,177,160],backgroundColor:'transparent',borderColor:'#007bff',pointBorderColor:'#007bff',pointBackgroundColor:'#007bff',fill:false},{type:'line',data:[60,80,70,67,80,77,100],backgroundColor:'tansparent',borderColor:'#ced4da',pointBorderColor:'#ced4da',pointBackgroundColor:'#ced4da',fill:false}]},options:{maintainAspectRatio:false,tooltips:{mode:mode,intersect:intersect},hover:{mode:mode,intersect:intersect},legend:{display:false},scales:{yAxes:[{gridLines:{display:true,lineWidth:'4px',color:'rgba(0, 0, 0, .2)',zeroLineColor:'transparent'},ticks:$.extend({beginAtZero:true,suggestedMax:200},ticksStyle)}],xAxes:[{display:true,gridLines:{display:false},ticks:ticksStyle}]}}})});

  
  

 


    //--------------
    //- AREA CHART -
    //--------------

    // Get context with jQuery - using jQuery's .get() method.
    var areaChartCanvas = $('#areaChart').get(0).getContext('2d')

    var areaChartData = {
      labels  : ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      datasets: [
        {
          label               : 'Digital Goods',
          backgroundColor     : 'rgba(60,141,188,0.9)',
          borderColor         : 'rgba(60,141,188,0.8)',
          pointRadius          : false,
          pointColor          : '#3b8bba',
          pointStrokeColor    : 'rgba(60,141,188,1)',
          pointHighlightFill  : '#fff',
          pointHighlightStroke: 'rgba(60,141,188,1)',
          data                : [28, 48, 40, 19, 86, 27, 90]
        },
        {
          label               : 'Electronics',
          backgroundColor     : 'rgba(210, 214, 222, 1)',
          borderColor         : 'rgba(210, 214, 222, 1)',
          pointRadius         : false,
          pointColor          : 'rgba(210, 214, 222, 1)',
          pointStrokeColor    : '#c1c7d1',
          pointHighlightFill  : '#fff',
          pointHighlightStroke: 'rgba(220,220,220,1)',
          data                : [65, 59, 80, 81, 56, 55, 40]
        },
      ]
    }

    var areaChartOptions = {
      maintainAspectRatio : false,
      responsive : true,
      legend: {
        display: false
      },
      scales: {
        xAxes: [{
          gridLines : {
            display : false,
          }
        }],
        yAxes: [{
          gridLines : {
            display : false,
          }
        }]
      }
    }

    // This will get the first returned node in the jQuery collection.
    new Chart(areaChartCanvas, {
      type: 'line',
      data: areaChartData,
      options: areaChartOptions
    })


  //-------------
  //- LINE CHART -
  //--------------
  var lineChartCanvas = $('#lineChart').get(0).getContext('2d')
  var lineChartOptions = $.extend(true, {}, areaChartOptions)
  var lineChartData = $.extend(true, {}, areaChartData)
  lineChartData.datasets[0].fill = false;
  lineChartData.datasets[1].fill = false;
  lineChartOptions.datasetFill = false

  var lineChart = new Chart(lineChartCanvas, {
    type: 'line',
    data: lineChartData,
    options: lineChartOptions
  })

  //-------------
  //- BAR CHART -
  //-------------
  var barChartCanvas = $('#barChart').get(0).getContext('2d')
  var barChartData = $.extend(true, {}, areaChartData)
  var temp0 = areaChartData.datasets[0]
  var temp1 = areaChartData.datasets[1]
  barChartData.datasets[0] = temp1
  barChartData.datasets[1] = temp0

  var barChartOptions = {
    responsive              : true,
    maintainAspectRatio     : false,
    datasetFill             : false
  }

  new Chart(barChartCanvas, {
    type: 'bar',
    data: barChartData,
    options: barChartOptions
  })

  //---------------------
  //- STACKED BAR CHART -
  //---------------------
  var stackedBarChartCanvas = $('#stackedBarChart').get(0).getContext('2d')
  var stackedBarChartData = $.extend(true, {}, barChartData)

  var stackedBarChartOptions = {
    responsive              : true,
    maintainAspectRatio     : false,
    scales: {
      xAxes: [{
        stacked: true,
      }],
      yAxes: [{
        stacked: true
      }]
    }
  }

  new Chart(stackedBarChartCanvas, {
    type: 'bar',
    data: stackedBarChartData,
    options: stackedBarChartOptions
  })