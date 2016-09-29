"use strict";

module.exports = function(grunt) {

    // Project configuration.
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        jshint: {
            files: ['Gruntfile.js', 'resources/js/*.js'],
            options: {
                jshintrc: '.jshintrc',
                globals: {
                    jQuery: true
                }
            }
        },
        concat: {
            js: {
                src: [
                    'resources/js/*.js',
                ],
                dest: 'resources/js/concat/scripts.js'
            },
            css: {
                src: [
                    'resources/css/*.css',
                ],
                dest: 'resources/css/concat/styles.css'
            }
        },
        uglify: {
            build: {
                src: 'resources/js/concat/*.js',
                dest: 'resources/build/<%= pkg.name %>.min.js'
            }
        },
        cssmin: {
            build: {
                src: 'resources/css/concat/*.css',
                dest: 'resources/build/<%= pkg.name %>.min.css'
            }
        },
        injector: {
            options: {
				addRootSlash: false
			},
            local_dependencies: {
                files: {
                    'index.html': ['resources/css/*.css', 'resources/js/*.js'],//['resources/build/*.*'],
                }
            }
        },
		wiredep: {

			task: {

			src: ['index.html']
			}
		},
        connect: {
            test: {
                options: {
                    port: 8000,
                    base: '.'
                }
            }
        }
    });
	
	

    // Load the plugin that provides the "uglify" task.
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-injector');
	grunt.loadNpmTasks('grunt-wiredep');
    grunt.loadNpmTasks('grunt-connect');

    // Default task(s).
    grunt.registerTask('default', ['injector', 'wiredep']);

};