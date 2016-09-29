 jQuery(document).ready(function() {
                jQuery('.inactive-stars').ewsRatingStars({ disabled: true });

                jQuery('.active-stars').ewsRatingStars({
                    disabled : false,
                    starNumber: 10,
                    onSetValue : function (obj, value) {
                        jQuery('#value').html('Current value: ' + value);
                    }
                });

                jQuery('.active-stars-green').ewsRatingStars({ class : 'ewsRatingStarColorGreen' });
                jQuery('.active-stars-red').ewsRatingStars({ class : 'ewsRatingStarColorRed' });
                jQuery('.active-stars-yellow').ewsRatingStars({
                    class : 'ewsRatingStarColorYellow',
                    defaultValue : 5
                });
            });