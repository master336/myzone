$(function(){
        var year = 0;
        //绑定年月
        function time() {
            $("#listyymm").empty();
            var date = new Date();
            var yy = date.getFullYear() + year;
            for (var i = 1; i <= 12; i++) {
                if (i < 10) {
                    i = "0" + i;
                }
                $("#listyymm").append("<option  value='Value'>" + yy + "年" + i + "月</option>");
            }
            var nowM=date.getMonth()+1;
            if(nowM<10){
                nowM="0"+nowM;
            }
           var nowYM= date.getFullYear()+"年"+nowM+"月";
            var options=$("#listyymm").find("option");
            $.each(options,function(i,item){
                if(item.innerHTML==nowYM){
                    $('#listyymm')[0].selectedIndex = i;
                };
            })

            //$('#listyymm')[0].selectedIndex = 0;
        };
        //绑定周
        function week(self) {
            var text = $(self).val();
            if (text != null && text != "") {
                var ymd = text.substring(0, 4) + "-" + text.substring(5, 7) + "-01";
                var week = new Date(Date.parse(ymd.replace(/\-/g, "/")));
                var w = week.toString().substring(0, 3);
                var yymm = new Date(text.substring(0, 4), text.substring(5, 7), 0);
                var day = yymm.getDate();
                var dd = 1;
                switch (w) {
                    case "Mon":
                        dd = 0;
                        break;
                    case "Tue":
                        dd = 1;
                        break;
                    case "Wed":
                        dd = 2;
                        break;
                    case "Thu":
                        dd = 3;
                        break;
                    case "Fri":
                        dd = 4;
                        break;
                    case "Sat":
                        dd = 5;
                        break;
                    case "Sun":
                        dd = 6;
                        break;
                }
                var aw = 5;
                if (day == 28 && dd == 0) {
                    aw = 4;
                }
                var i = 1;
                $(self).parent().find('.dropweek').empty();
                for (var i = 0; i < aw; i++) {
                    var start = i * 7 + 1 - dd;
                    var end = i * 7 + 7 - dd;
                    if (start < 1) {
                        start = 1;
                    }

                    if (end > day) {
                        end = day;
                    }
                    if (start < 10) {
                        start = "0" + start;
                    }
                    if (end < 10) {
                        end = "0" + end;
                    }
                    var str = ("第" + (i + 1) + "周[" + text.substring(5, 7) + "月" + start + "号—" + text.substring(5, 7) + "月" + end + "号]").toString();
                    $(self).parent().find('.dropweek').append("<option value='Value'>" + str + "</option>");
                }
                $(self).parent().find('.dropweek')[0].selectedIndex = 0;

            }
        }
            //文本框单击事件
            var self;
            $(".cusdate").click(function(e) {
                self=this;
                $("#dateLayout").css("display", "block");
                var top = $(self).offset().top;
                top += 25;
                var left = $(self).offset().left;
                $("#dateLayout").css("top", top);
                $("#dateLayout").css("left", left);
                time();

            });
            //确定按钮
            $("#btnhide").click(function(e) {
                var dat = $("#listyymm").find("option:selected").text();
                $(self).val(dat.toString());
                if($(self).parent().find(".dropweek").length>0){
                    week(self);
                }
                $("#dateLayout").css("display", "none");
            });
            //清除按钮
            $("#btncancel").click(function(e) {
                    $(self).val('');
                    $(self).parent().find(".dropweek").text('');
                    $("#dateLayout").css("display", "none");
                })
                //上下一年按钮
            $("#btndown").click(function() {
                year += 1;
                time();
            });
            $("#btnup").click(function() {
                year -= 1;
                time();
            });
            //dropdownonchange

            $(".dropweek").change(function(e) {
                var itme = $(self).parent().find(".dropweek").find("option:selected").text();
            });
            $("#listyymm").change(function(e) {
                var dat = $("#listyymm").find("option:selected").text();
                $(self).val(dat.toString());
                if($(self).parent().find(".dropweek").length>0){
                    week(self);
                }
                $("#dateLayout").css("display", "none");
            });
}) 