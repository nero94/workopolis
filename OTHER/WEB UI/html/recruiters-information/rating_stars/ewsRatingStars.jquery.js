(function($) {
    $.fn.ewsRatingStars = function(options) {
        var defaults = {
            name : 'rating_stars',                  // Name of stars input
            defaultValue : 0,                       // Default value

            disabled : false,                       // Is stars disabled or not

            starWidth : 36,                         // Default star width
            starHeight : 28,                        // Default star height
            starNumber : 5,                         // Number of stars
            starSpaces : 0,                         // Spaces between stars in pixels

            normalPosition : { x : 0, y : 0 },      // Normal star position inside background sprite
            activePosition : { x : 0, y : 28 },     // Hover star position inside background sprite
            hoverPosition : { x : 0, y : 58 },      // Active star position inside background sprite

            class : '',                             // Additional class - used to change star image sprite

            // Callback methods
            onSetValue : null,                      // Called when value is set
            onUnset : null                          // Called when rating is unset
        };

        if (this.length > 0) {
            return this.each(function () {

                var opts = $.extend(defaults, options);

                var starsObj = $(this);

                if (opts.disabled)
                    var isDisabled = true;
                else
                    var isDisabled = false;

                var starsUl = $('<ul>',
                    {
                        class: 'ewsRatingStars',
                        css: {
                            height: opts.starHeight + 2 * opts.starSpaces
                        }
                    }
                ).appendTo($(this));

                var cssClass = opts.class  || '';

                for (var i = 0; i < opts.starNumber; i++) {
                    $('<li>',
                        {
                            class: 'ewsRatingStar ' + cssClass,
                            'data-value': i + 1,
                            css: {
                                width: opts.starWidth,
                                height: opts.starHeight,
                                margin: opts.starSpaces
                            }
                        }
                    ).appendTo(starsUl);
                }

                var input = $('<input>',
                    {
                        type: 'hidden',
                        name: opts.name,
                        value: 0
                    }
                );

                starsUl.after(input);

                var dataDefault = $(this).attr('data-default');
                opts.defaultValue = dataDefault || opts.defaultValue;

                setValue(opts.defaultValue);
                redrawStars();

                if (!isDisabled) {
                    $('.ewsRatingStar', $(this)).unbind().bind({
                        mouseenter: function (e) {
                            for (var i = 0; i <= $(this).index(); i++) {
                                setBackgroundPosition(opts.hoverPosition, $(this).parent().children().eq(i));
                            }
                        },
                        mouseleave: function (e) {
                            redrawStars();
                        },
                        mouseover: function (e) {
                            $(this).css('cursor', 'pointer');
                        },
                        click: function (e) {
                            setValue($(this).attr('data-value'));
                            redrawStars();
                        }
                    });
                }

                function redrawStars() {
                    var value = getValue();

                    for (var i = 0; i < opts.starNumber; i++) {

                        var position = opts.normalPosition;

                        if (i <= (value - 1)) {
                            position = opts.activePosition;
                        }

                        setBackgroundPosition(position, starsObj.find('ul').children().eq(i));
                    }
                }

                function setBackgroundPosition(position, obj) {
                    obj.css('background-position', -position.x + 'px ' + -position.y + 'px');
                }

                function setValue(value) {
                    if (getValue() == value && value != 0) {
                        if(opts.onUnset) opts.onUnset( starsObj, value );
                        setValue(0);
                    } else {
                        if(opts.onSetValue) opts.onSetValue( starsObj, value );
                        starsObj.find('input').val(value);
                    }
                }

                function getValue() {
                    return starsObj.find('input').val();
                }
            });
        }
    }
})(jQuery);