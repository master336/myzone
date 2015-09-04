var isMobile = false;
var isDesktop = false;
$(window).on("load resize", function (a) {
    if (Modernizr.mq("only all and (max-width: 767px)")) {
        isMobile = true
    } else {
        isMobile = false
    }
    if (Modernizr.mq("only all and (max-width: 1024px)")) {
        isDesktop = false
    } else {
        isDesktop = true
    }
    toTop(isMobile)
});
$(window).resize(function () {
    Modernizr.addTest("ipad", function () {
        return !!navigator.userAgent.match(/iPad/i)
    });
    if (!Modernizr.ipad) {
        initializeMainMenu()
    }
});
$(document).ready(function () {
    initializeMainMenu();
    if ($("#mapWrapper").length) {
        appendBootstrap()
    }
    if ($("ul#og-grid").length) {
        Grid.init()
    }
    if (!Modernizr.input.placeholder) {
        $("[placeholder]").focus(function () {
            var a = $(this);
            if (a.val() == a.attr("placeholder")) {
                a.val("");
                a.removeClass("placeholder")
            }
        }).blur(function () {
            var a = $(this);
            if (a.val() == "" || a.val() == a.attr("placeholder")) {
                a.addClass("placeholder");
                a.val(a.attr("placeholder"))
            }
        }).blur();
        $("[placeholder]").parents("form").submit(function () {
            $(this).find("[placeholder]").each(function () {
                var a = $(this);
                if (a.val() == a.attr("placeholder")) {
                    a.val("")
                }
            })
        })
    }
    if ($("a.image-link").length) {
        $("a.image-link").click(function (c) {
            var b = [];
            b.push({src: $(this).attr("href")});
            if ($(this).data("gallery")) {
                var a = $(this).data("gallery").split(",");
                $.each(a, function (e, d) {
                    b.push({src: d})
                })
            }
            $.magnificPopup.open({type: "image", mainClass: "mfp-fade", items: b, gallery: {enabled: true}});
            c.preventDefault()
        })
    }
    if ($("a.image-iframe").length) {
        $("a.image-iframe").magnificPopup({type: "iframe", mainClass: "mfp-fade"})
    }
    $(".tips").tooltip({placement: "auto"});
    if ($(".nekoDataOwl").length) {
        $(".nekoDataOwl").each(function (a) {
            $(this).owlCarousel({
                items: $(this).data("neko_items"),
                navigation: $(this).data("neko_navigation"),
                singleItem: $(this).data("neko_singleitem"),
                autoPlay: $(this).data("neko_autoplay"),
                itemsScaleUp: $(this).data("neko_itemsscaleup"),
                navigationText: ['<i class="fa fa-angle-left"></i>', '<i class="fa fa-angle-right"></i>'],
                pagination: $(this).data("neko_pagination"),
                paginationNumbers: $(this).data("neko_paginationnumbers"),
                autoHeight: $(this).data("neko_autoheight"),
                mouseDrag: $(this).data("neko_mousedrag"),
                transitionStyle: $(this).data("neko_transitionstyle"),
                itemsDesktop: false
            })
        })
    }
    if ($("#flexHome").length) {
        $("#flexHome").flexslider({
            animation: "fade",
            controlNav: true,
            directionNav: false,
            touch: true,
            direction: "vertical"
        })
    }
    if ($(".flexFullScreen").length) {
        $(".flexFullScreen").flexslider({
            animation: "fade",
            controlNav: true,
            directionNav: true,
            slideshow: true,
            touch: true,
            prevText: '<i class="fa fa-angle-left"></i>',
            nextText: '<i class="fa fa-angle-right"></i>',
            start: function (a) {
                setTimeout("animateTxt(" + a.currentSlide + ", 'in')", 100)
            },
            before: function (a) {
                setTimeout("animateTxt(" + a.currentSlide + ")", 100)
            },
            after: function (a) {
                setTimeout("animateTxt(" + a.currentSlide + ", 'in')", 100)
            }
        })
    }
    if ($(".camera_wrap").length) {
        jQuery(".camera_wrap").camera({
            thumbnails: true,
            pagination: true,
            playPause: false,
            height: "50%",
            fx: "simpleFade"
        })
    }
    if ($(".camera_wrap_nonav").length) {
        jQuery(".camera_wrap_nonav").camera({pagination: false, thumbnails: true, height: "70%"})
    }
    if ($(".camera_wrap_nothumb").length) {
        jQuery(".camera_wrap_nothumb").camera({pagination: true, thumbnails: false, height: "40%"})
    }
    if ($(".imgHover").length) {
        $(".imgHover article").hover(function () {
            var b = $(this);
            var a = ($("img", b).height() / 2 - $(".iconLinks", b).height() / 2);
            $(".iconLinks", b).css("margin-top", a);
            $(".mask", this).css("width", $("img", this).outerWidth(true));
            if ($(".pinBox").length) {
                $(".mediaHover", b).height($("img", b).outerHeight(true) + 15);
                $(".mask", this).css("height", $("img", this).outerHeight(true) + 15)
            } else {
                $(".mediaHover", b).height($("img", b).outerHeight(true));
                $(".mask", this).css("height", $("img", this).outerHeight(true));
                if (b.hasClass("minimalBox")) {
                    $(".mask", this).css("left", "0");
                    $(".mask", this).css("top", "0")
                }
            }
            $(".mask", this).css("margin-top", 0);
            $(".mask", this).stop(1).animate({opacity: 0.5}, 200, "easeOutQuad", function () {
                $(".iconLinks", b).css("display", "block");
                if (Modernizr.csstransitions) {
                    $(".iconLinks a").addClass("animated");
                    $(".iconLinks a", b).removeClass("flipOutX");
                    $(".iconLinks a", b).addClass("bounceInDown")
                } else {
                    $(".iconLinks", b).stop(true, false).fadeIn("fast")
                }
            })
        }, function () {
            var a = $(this);
            $(".mask", this).stop(1).animate({opacity: 0}, 200, "easeOutQuad", function () {
                if (Modernizr.csstransitions) {
                    $(".iconLinks a", a).removeClass("bounceInDown");
                    $(".iconLinks a", a).addClass("flipOutX")
                } else {
                    $(".iconLinks", a).stop(true, false).fadeOut("fast")
                }
            })
        })
    }
    $(".socialIcon").hover(function () {
        $(this).stop(true, true).addClass("socialHoverClass", 300)
    }, function () {
        $(this).removeClass("socialHoverClass", 300)
    });
    $(".tabs li, .accordion h2").hover(function () {
        $(this).stop(true, true).addClass("speBtnHover", 300)
    }, function () {
        $(this).stop(true, true).removeClass("speBtnHover", 100)
    });
    $(".alert").delegate("button", "click", function () {
        $(this).parent().fadeOut("fast")
    });
    if ($(".boxIcon").length) {
        $(".boxIcon").hover(function () {
            var a = $(this);
            a.css("opacity", "1");
            a.addClass("hover");
            $(".boxContent>p").css("bottom", "-50px");
            a.find(".boxContent>p").stop(true, false).css("display", "block");
            a.find(".iconWrapper i").addClass("triggeredHover");
            a.find(".boxContent>p").stop(true, false).animate({"margin-top": "0px"}, 300, function () {
            })
        }, function () {
            var a = $(this);
            a.removeClass("hover");
            a.find(".boxContent>p").stop(true, false).css("display", "none");
            a.find(".boxContent>p").css("margin-top", "250px");
            a.find(".iconWrapper i").removeClass("triggeredHover")
        })
    }
    if ($("#shareme-classic").length) {
        $("#shareme-classic").sharrre({
            share: {googlePlus: true, facebook: true, twitter: true, linkedin: true},
            buttons: {
                googlePlus: {size: "tall", annotation: "bubble"},
                facebook: {layout: "box_count"},
                twitter: {count: "vertical"},
                linkedin: {counter: "top"}
            },
            enableHover: false,
            enableCounter: false,
            enableTracking: true
        })
    }
    if ($("#shareme").length) {
        $("#shareme").sharrre({
            share: {twitter: true, facebook: true, googlePlus: true},
            template: '<div class="box"><h4>Share this:</h4><a href="#" class="facebook"><i class="icon-facebook-1"></i></a><a href="#" class="twitter"><i class="icon-twitter-bird"></i></a><a href="#" class="googleplus"><i class="icon-gplus-1"></i></a></div>',
            enableHover: false,
            enableTracking: true,
            render: function (b, a) {
                $(b.element).on("click", ".twitter", function () {
                    b.openPopup("twitter")
                });
                $(b.element).on("click", ".facebook", function () {
                    b.openPopup("facebook")
                });
                $(b.element).on("click", ".googleplus", function () {
                    b.openPopup("googlePlus")
                })
            }
        })
    }
    if ($(".previewTrigger").length) {
        $(".mask").css("height", $(".previewTrigger").height());
        $(".mask").css("width", $(".previewTrigger").width());
        $(".previewTrigger").hover(function () {
            var a = $(this);
            a.children(".mask").fadeIn("fast");
            if (Modernizr.csstransitions) {
                $(".iconWrapper", a).addClass("animated");
                $(".iconWrapper", a).css("display", "block");
                $(".iconWrapper", a).removeClass("flipOutX");
                $(".iconWrapper", a).addClass("bounceInDown")
            } else {
                $(".iconWrapper", a).stop(true, false).fadeIn("fast")
            }
        }, function () {
            var a = $(this);
            a.children(".mask").fadeOut("fast");
            if (Modernizr.csstransitions) {
                $(".iconWrapper", a).removeClass("bounceInDown");
                $(".iconWrapper", a).addClass("flipOutX");
                $(".iconWrapper", a).css("display", "none");
                $(".iconWrapper", a).removeClass("animated")
            } else {
                $(".iconWrapper", a).stop(true, false).fadeOut("fast")
            }
        })
    }
    $("#resMainMenu .nav a").on("click", function () {
        $(".navbar-toggle").click()
    })
});
$(window).load(function () {
    if ($("#status").length) {
        $("#status").fadeOut();
        $("#preloader").delay(350).fadeOut("slow");
        $("body").delay(350).css({overflow: "visible"})
    }
    if ($(".isotopeWrapper").length) {
        var b = $(".isotopeWrapper");
        var a = $(".isotopeWrapper").attr("id");
        b.isotope({
            layoutMode: "sloppyMasonry",
            itemSelector: ".isotopeItem",
            resizable: false,
            masonry: {columnWidth: b.width() / a}
        });
        $("#filter a").click(function (d) {
            $("#filter a").removeClass("current");
            $(this).addClass("current");
            var c = $(this).attr("data-filter");
            b.isotope({filter: c, animationOptions: {duration: 1000, easing: "easeOutQuart", queue: false}});
            if (isDesktop === true && $('section[id^="paralaxSlice"]').length) {
                setTimeout(function () {
                    $.stellar("refresh")
                }, 1000)
            }
            d.preventDefault();
            return false
        });
        $(window).smartresize(function () {
            b.isotope({masonry: {columnWidth: b.width() / a}})
        })
    }
    $(".iconBoxV3 a").hover(function () {
        if (Modernizr.csstransitions) {
            $(this).stop(false, true).toggleClass("hover", 150);
            $("i", this).css("-webkit-transform", "rotateZ(360deg)");
            $("i", this).css("-moz-transform", "rotateZ(360deg)");
            $("i", this).css("-o-transform", "rotateZ(360deg)");
            $("i", this).css("transform", "rotateZ(360deg)")
        } else {
            $(this).stop(false, true).toggleClass("hover", 150)
        }
    }, function () {
        if (Modernizr.csstransitions) {
            $(this).stop(false, true).toggleClass("hover", 150);
            $("i", this).css("-webkit-transform", "rotateZ(0deg)");
            $("i", this).css("-moz-transform", "rotateZ(0deg)");
            $("i", this).css("-o-transform", "rotateZ(0deg)");
            $("i", this).css("transform", "rotateZ(0deg)")
        } else {
            $(this).stop(false, true).toggleClass("hover", 150)
        }
    });
    if ($(".scrollMenu").length) {
        if ($(".localscroll").length) {
            $(".localscroll").localScroll({lazy: true, offset: {top: -100}})
        }
    }
});
jQuery(function () {
    if (jQuery("#contactfrm").length) {
        jQuery("#contactfrm").validate({
            errorPlacement: function (a, b) {
                a.insertBefore(b)
            },
            submitHandler: function (a) {
                jQuery(a).ajaxSubmit({target: ".result"})
            },
            onkeyup: false,
            onclick: false,
            rules: {
                name: {required: true, minlength: 3},
                email: {required: true, email: true},
                phone: {required: true, minlength: 10, digits: true},
                category: {required: true},
                comment: {required: true, minlength: 10, maxlength: 350}
            }
        })
    }
    if (jQuery("#projectQuote").length) {
        jQuery("#projectQuote").validate({
            errorPlacement: function (a, b) {
                a.insertBefore(b)
            },
            submitHandler: function (a) {
                jQuery(a).ajaxSubmit({target: ".quoteResult"})
            },
            onkeyup: false,
            rules: {
                name: {required: true, minlength: 3},
                email: {required: true, email: true},
                company: {required: true, minlength: 2},
                quoteType: {required: true},
                comment: {required: true, minlength: 10, maxlength: 350}
            }
        })
    }
});
function animateTxt(b, e) {
    if (e === "in") {
        var d = 0;
        var a = 0;
        $(".slideN" + b + ":not([class*=clone])>.caption").css("display", "block");
        $(".slideN" + b + ":not([class*=clone])>.caption>div").each(function () {
            if (Modernizr.csstransitions) {
                $(this).css("-webkit-animation-delay", a + "s");
                $(this).css("-moz-animation-delay", a + "s");
                $(this).css("-0-animation-delay", a + "s");
                $(this).css("-ms-animation-delay", a + "s");
                $(this).css("animation-delay-delay", a + "s");
                $(this).show().addClass("animated").addClass($(this).attr("data-animation"))
            } else {
                var f;
                $(".slideN" + b + ":not([class*=clone])>.caption>div").hide();
                if (d === 0) {
                    f = 0
                } else {
                    if (d === 1) {
                        f = 300
                    } else {
                        f = 300 * d
                    }
                }
                $(this).delay(f).fadeIn("fast")
            }
            d++;
            a = a + 0.2
        })
    } else {
        var c = 0;
        $(".slideN" + b + ":not([class*=clone])>.caption").css("display", "none");
        $(".slideN" + b + ":not([class*=clone])>.caption>div").each(function () {
            if (Modernizr.csstransitions) {
                $(this).removeClass($(this).attr("data-animation")).removeClass("animated").hide()
            } else {
                $(this).hide()
            }
            c++
        })
    }
}
function initializeMainMenu() {
    var b = $("#mainMenu").children("ul");
    if (Modernizr.mq("only all and (max-width: 767px)")) {
        var a = false;
        $("a.hasSubMenu").unbind("click");
        $("li", b).unbind("mouseenter mouseleave");
        $("a.hasSubMenu").on("click", function (c) {
            c.preventDefault();
            a = $(this).parent("li").hasClass("Nactive");
            if ($(this).parent("li").hasClass("primary")) {
                $("li", b).removeClass("Nactive")
            } else {
                $("li:not(.primary)", b).removeClass("Nactive")
            }
            if (!a) {
                $(this).parents("li").addClass("Nactive")
            } else {
                $(this).parent().parent("li").addClass("Nactive")
            }
            return
        })
    } else {
        if (Modernizr.mq("only all and (max-width: 1024px)") && Modernizr.touch) {
            $("a.hasSubMenu").attr("href", "");
            $("a.hasSubMenu").on("touchend", function (c) {
                var f = $(this).parent(), d = f.children(".subMenu");
                if ($(this).data("clicked_once")) {
                    if (f.parent().is($(":gt(1)", b))) {
                        if (d.css("display") == "block") {
                            f.removeClass("hover");
                            d.css("display", "none")
                        } else {
                            $(".subMenu").css("display", "none");
                            d.css("display", "block")
                        }
                    } else {
                        $(".subMenu").css("display", "none")
                    }
                    $(this).data("clicked_once", false)
                } else {
                    f.parent().find("li").removeClass("hover");
                    f.addClass("hover");
                    if (f.parent().is($(":gt(1)", b))) {
                        f.parent().find(".subMenu").css("display", "none");
                        d.css("left", d.parent().outerWidth(true));
                        d.css("display", "block")
                    } else {
                        $(".subMenu").css("display", "none");
                        d.css("display", "block")
                    }
                    $("a.hasSubMenu").data("clicked_once", false);
                    $(this).data("clicked_once", true)
                }
                c.preventDefault();
                return false
            });
            window.addEventListener("orientationchange", function () {
                $("a.hasSubMenu").parent().removeClass("hover");
                $(".subMenu").css("display", "none");
                $("a.hasSubMenu").data("clicked_once", false)
            }, true)
        } else {
            $("li", b).removeClass("Nactive");
            $("a", b).unbind("click");
            $("li", b).hover(function () {
                var c = $(this), d = c.children(".subMenu");
                if (d.length) {
                    c.addClass("hover").stop()
                } else {
                    if (c.parent().is($(":gt(1)", b))) {
                        c.stop(false, true).fadeIn("slow")
                    }
                }
                if (c.parent().is($(":gt(1)", b))) {
                    d.stop(true, true).fadeIn(200, "easeInOutQuad");
                    d.css("left", d.parent().outerWidth(true))
                } else {
                    d.stop(true, true).delay(50).fadeIn(200, "easeInOutQuad")
                }
            }, function () {
                var c = $(this), d = c.children("ul");
                if (c.parent().is($(":gt(1)", b))) {
                    c.children("ul").hide();
                    c.children("ul").css("left", 0)
                } else {
                    c.removeClass("hover");
                    $(".subMenu").stop(true, true).delay(300).fadeOut()
                }
                if (d.length) {
                    c.removeClass("hover")
                }
            })
        }
    }
}
jQuery(function (a) {
    if (a("#superSizedSlider").length) {
        a("#superSizedSlider").height(a(window).height());
        a.supersized({
            slideshow: 1,
            autoplay: 1,
            start_slide: 1,
            stop_loop: 0,
            random: 0,
            slide_interval: 12000,
            transition: 1,
            transition_speed: 1000,
            new_window: 1,
            pause_hover: 0,
            keyboard_nav: 1,
            performance: 1,
            image_protect: 1,
            min_width: 0,
            min_height: 0,
            vertical_center: 1,
            horizontal_center: 1,
            fit_always: 0,
            fit_portrait: 1,
            fit_landscape: 0,
            slide_links: "blank",
            thumb_links: 0,
            thumbnail_navigation: 0,
            slides: [{
                image: "./images/slider/super/supersized-1.jpg",
                title: '<h1>We create the future</h1><a href="#team" class="btn btn-primary">learn more</a>',
                thumb: "",
                url: ""
            }, {
                image: "./images/slider/super/supersized-2.jpg",
                title: '<h1>we help your brand grow</h1><a href="#works" class="btn btn-primary">check our portfolio</a>',
                thumb: "",
                url: ""
            }, {
                image: "./images/slider/super/supersized-3.jpg",
                title: '<h1>That\'s the way it is, baby</h1><a href="#contact" class="btn btn-primary">call us</a>',
                thumb: "",
                url: ""
            }],
            progress_bar: 0,
            mouse_scrub: 0
        })
    }
});
function toTop(a) {
    if (a == false) {
        if (!$("#nekoToTop").length) {
            $("body").append('<a href="#" id="nekoToTop"><i class="fa fa-angle-up"></i></a>')
        }
        $(window).scroll(function () {
            if ($(this).scrollTop() > 100) {
                $("#nekoToTop").slideDown("fast")
            } else {
                $("#nekoToTop").slideUp("fast")
            }
        });
        $("#nekoToTop").click(function (b) {
            b.preventDefault();
            $("html, body").animate({scrollTop: 0}, 800, "easeInOutCirc")
        })
    } else {
        if ($("#nekoToTop").length) {
            $("#nekoToTop").remove()
        }
    }
}
(function (a) {
    a.fn.floatLabels = function (d) {
        var c = this;
        var e = a.extend({}, d);

        function b() {
            c.on("input keyup change", "input, textarea", function () {
                g.swapLabels(this)
            })
        }

        var g = {
            initialize: function () {
                c.each(function () {
                    var k = a(this);
                    var h = k.children("label");
                    var j = k.find("input,textarea").first();
                    if (k.children().first().is("label")) {
                        k.children().first().remove();
                        k.append(h)
                    }
                    var i = (j.attr("placeholder") && j.attr("placeholder") != h.text()) ? j.attr("placeholder") : h.text();
                    h.data("placeholder-text", i);
                    h.data("original-text", h.text());
                    if (j.val() == "") {
                        j.addClass("empty")
                    }
                })
            }, swapLabels: function (j) {
                var i = a(j);
                var h = a(j).siblings("label").first();
                var k = Boolean(i.val());
                if (k) {
                    i.removeClass("empty");
                    h.text(h.data("original-text"))
                } else {
                    i.addClass("empty");
                    h.text(h.data("placeholder-text"))
                }
            }
        };

        function f() {
            b();
            g.initialize();
            c.each(function () {
                g.swapLabels(a(this).find("input,textarea").first())
            })
        }

        f();
        return this
    };
    a(function () {
        a(".float-label-control").floatLabels()
    })
})(jQuery);
$(document).ready(function () {
    $("input").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green", increaseArea: "20%"})
});
   <!-- 个人投资人、投资机构切换js -->
    inputs = $(".radio-inline").find("input");
    inputs.eq(0).iCheck('check');
    $("."+inputs.eq(1).attr("class")+"_active").hide();
    inputs.on('ifChecked',function(){
    var input = $(this);
    inputs.each(function(){
        if(input.attr("class")==$(this).attr("class")){
           $("."+$(this).attr("class")+"_active").show();
           $("."+$(this).attr("class")+"_active").find("input").eq(0).iCheck('check');
        }else{
           $("."+$(this).attr("class")+"_active").hide();
        }
    });



});



$(function () {
    $("input.date").datetimepicker({
        format: "yyyy/mm/dd",
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $("input.datetime1").datetimepicker({
        format: "yyyy/mm/dd-hh:ii",
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
    $("input.datetime2").datetimepicker({
        format: "yyyy/mm/dd-hh:ii",
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    })
});
$(function () {
    $("[data-toggle='tooltip']").tooltip()
});
$(window).on("load", function () {
    $(".selectpicker").selectpicker("show")
});
$(".vaForm").Validform();
