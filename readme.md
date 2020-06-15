# lib-gis-address-parser

**Utility class to parse and standardize address information.**

Address standardization is the process that takes an address
and converts it to a standard format by analyzing the several components.
Standardization is not correction ! Standardization is based on syntax
correction (dictionnaries, spellchecking, synonyms), while correction
is based on postal reference data : correction checks if each element
exists and if the combination is correct.

## Background

This project is based upon **jgeocoder**, an attempt to build a Java Geocoder
using the US Census Tiger database.
Credit for jgeocoder goes to _zl25-drexel_ (whomever that is). For context,
zl25-drexel also built the "Geo-Google - Address Standardizer", a web client
that provides the same (or similar) functionality.

jgeocoder was created 2008-04-03 and appears to have been abandoned shortly
thereafter. No updates have been made since the last SVN commit in July 2008.
A similar project with the same source code was posted to github under another
author in 2009.
jgeocoder itself was modeled after the PERL CPAN _Geo::Coder::US_ module.

This code fork reorganizes classes into a logical hierarchy, renames
many classes according to their logical function, adds and improves class
and method documentation, and simplifies access to the parser via a standard
utility class named `AddressParser`. Content is also returned in a standard `Address`
data transfer object.

`jgeocoder` was released under the Apache license. This build preserves
the same license.


## History:
  09/06/12 - version 1.0 committed
             parse raw, formatted output, WSIF interchange all working

  v0.9.0 - reorganize into maven library; works fine most all of the time
  v0.9.1 - relax error conditions
  v0.9.2 - replace WSIF address with commons GISAddress
  v0.9.3 - refresh dependencies to latest
  v0.9.4 - make more self contained

  v1.0.0 -  depends upon GIS parent  v2.x
  - refactor using Address DTO (replaces GISAddress)

  v2.0.0 - refactor all to 'ch.keybridge.gis' base package
  - use local Address DTO, remove fleeting dependency on lib-gis-dto


## References:

  * [jgeocoder](http://sourceforge.net/projects/jgeocoder/files/)

Other address parser utilities you might find useful:

  * [biggs](https://github.com/yolk/biggs)   biggs is a small ruby gem/rails plugin for formatting postal addresses from over 60 countries.

